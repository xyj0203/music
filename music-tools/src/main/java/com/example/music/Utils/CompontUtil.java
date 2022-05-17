package com.example.music.Utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Slf4j
@Component
public class CompontUtil {
    public static String email = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    //发送人
    @Value("${spring.mail.username}")
    private String sendUser;
    @Autowired
    public JavaMailSender javaMailSender;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 发送邮件
     *
     * @return
     */
    public String sendEmail(String toUser) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            StringBuilder stringBuilder = new StringBuilder("您本次的验证码为: ");
            Random random = new Random();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                int code = random.nextInt(10);
                sb.append(code);
            }
            stringBuilder.append(sb.toString());
            stringBuilder.append("  ，如非本人操作，请忽略此邮件。（五分钟内有效）。");
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, false);
            mimeMessageHelper.setFrom(sendUser);
            mimeMessageHelper.setTo(toUser);
            mimeMessageHelper.setSubject("验证码邮件");
            mimeMessageHelper.setText(stringBuilder.toString(), true);
            javaMailSender.send(message);
            boolean set = redisUtils.set(toUser, sb.toString(), 300);
            return set == true ? sb.toString() : null;
        } catch (Exception e) {
            log.info("{}发送失败", toUser);
            e.printStackTrace();
            return null;
        }
    }

    //计算两个日期相差年数
    public int yearDateDiff(long startDate, long endDate) {
        Calendar calStartTime = Calendar.getInstance(); //获取日历实例
        Calendar calEndTime = Calendar.getInstance();
        calStartTime.setTime(new Date(startDate)); //字符串按照指定格式转化为日期
        calEndTime.setTime(new Date(endDate));
        return calEndTime.get(Calendar.YEAR) - calStartTime.get(Calendar.YEAR);
    }

    public Long getRandomLong(Long min, Long max) {
        Random random = new Random();
        return random.nextLong() % (max - min + 1) + min;
    }

    //处理lrc文件读取数据至数据ku
}