package com.example.music.Entity.Pojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
@Getter
@Setter
@ToString
public class ResultObjectModel<T>{
    /**
     * 返回是否成功
     */
    private int code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 返回数据内容
     */
    private T data;

    public ResultObjectModel() {
    }

    public ResultObjectModel(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 返回成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultObjectModel success(T data){
        return new ResultObjectModel(200 , "请求成功" , data);
    }

    /**
     * 返回成功
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultObjectModel success(String message ,T data){
        return new ResultObjectModel(200 , message , data);
    }

    /**
     * 返回成功
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResultObjectModel success(String message ){
        return new ResultObjectModel(200 , message , null);
    }

    /**
     * 失败
     * @param message
     * @return
     */
    public static ResultObjectModel fail(String message){
        return new ResultObjectModel(500 , message , null);
    }

    /**
     * 失败
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultObjectModel fail(String message ,T data){
        return new ResultObjectModel(500 , message , data);
    }

    public static void returnvalue(HttpServletRequest request, HttpServletResponse response,ResultObjectModel resultObjectModel) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try{
            mapper.writeValue(response.getWriter(), resultObjectModel);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
