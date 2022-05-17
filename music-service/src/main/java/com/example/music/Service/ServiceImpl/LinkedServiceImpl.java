package com.example.music.Service.ServiceImpl;

import com.example.music.Entity.Pojo.Entity.Apply;
import com.example.music.Entity.Pojo.Entity.User;
import com.example.music.Entity.Pojo.ResultObjectModel;
import com.example.music.Entity.Pojo.Vo.ApplyVo;
import com.example.music.Entity.Pojo.Vo.UserVo;
import com.example.music.Entity.Util.UserType;
import com.example.music.Mapper.BasicMapper;
import com.example.music.Mapper.LinkedMapper;
import com.example.music.Service.LinkedService;
import com.example.music.Utils.BeanUtils;
import com.example.music.Utils.CompontUtil;
import com.example.music.Utils.RedisKeyUtils;
import com.example.music.Utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LinkedServiceImpl implements LinkedService {

    @Resource
    private LinkedMapper linkedMapper;
    @Resource
    private BasicMapper basicMapper;
    @Autowired
    private CompontUtil compontUtil;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private BeanUtils beanUtils;


    @Override
    public boolean isLinked(Long userId, Long friendId) {
        if (linkedMapper.isLinked(userId, friendId) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public ResultObjectModel searchLinkedMan(String account) {
        User user = basicMapper.selectUserByAccount(account);
        if (user == null) {
            return ResultObjectModel.fail("没有该用户");
        }
        UserVo userVo = beanUtils.userToUserVo(user);
        return ResultObjectModel.success(userVo);
    }

    @Override
    public ResultObjectModel addLinkedMan(Long userId, Long friendId,String sendContent) {
        if (isLinked(userId, friendId)) {
            return ResultObjectModel.fail("已经是好友");
        }
        Apply apply = new Apply(null,userId,friendId,sendContent);
        int i = linkedMapper.addLinkedMan(apply);
        if (i == 1){
            return ResultObjectModel.success("发送申请成功");
        }
        return ResultObjectModel.success("发送申请失败");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultObjectModel acceptLinkedMan(Long userId, Long friendId, Integer status,Long applyId) {
        if (status == 0){
            return ResultObjectModel.fail("状态不能修改");
        }
        if (isLinked(userId, friendId)) {
            return ResultObjectModel.fail("已经是好友");
        }
        int i = linkedMapper.updateApplyStatus(applyId, status);
        if (i == 1){
            if(status == 1){
                int i1 = linkedMapper.addLinkedMantogether(userId, friendId);
                int i2 = linkedMapper.addLinkedMantogether(friendId, userId);
                if (i1 == 1 && i2 == 1){
                    return ResultObjectModel.success("添加好友成功");
                }
                return ResultObjectModel.success("添加好友失败");
            }
        }else{
            return ResultObjectModel.success("状态修改失败");
        }
        return ResultObjectModel.success("状态修改失败");
    }

    @Override
    public ResultObjectModel getApplyList(Long userId) {
        System.out.println("userId:"+userId);
        List<ApplyVo> applyVos = linkedMapper.queryApplyList(userId);
        System.out.println(applyVos.size());
        for (ApplyVo applyVo : applyVos) {
            switch (applyVo.getApplyState()){
                case 0:
                    applyVo.setApplyStateStr("待处理");
                    break;
                case 1:
                    applyVo.setApplyStateStr("已同意");
                    break;
                case 2:
                    applyVo.setApplyStateStr("已拒绝");
                    break;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(applyVo.getApplyTime());
            applyVo.setApplyTimeStr(format);
            applyVo.setApplyUserSex(applyVo.getApplyUserSexValue() == 0 ? "男" : "女");
            if (applyVo.getApplyUserBirthdayTime() == null){
                applyVo.setApplyUserAge(0);
            }else{
                applyVo.setApplyUserAge(compontUtil.yearDateDiff(applyVo.getApplyUserBirthdayTime().getTime(),System.currentTimeMillis()));
            }
        }
        return ResultObjectModel.success(applyVos);
    }

    @Override
    public ResultObjectModel getLinkedManList(Long userId) {
        List<User> users = linkedMapper.queryLinkedManList(userId);
        List<UserVo> userVos = new ArrayList<>();
        for (User user : users) {
            UserVo userVo = beanUtils.userToUserVo(user);
            userVos.add(userVo);
        }
        return ResultObjectModel.success(userVos);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultObjectModel deleteLinkedMan(Long userId) {
        int i = linkedMapper.deleteLinkedMan(userId);
        if (i == 2){
            return ResultObjectModel.success("删除成功");
        }
        return ResultObjectModel.fail("删除失败");
    }
}
