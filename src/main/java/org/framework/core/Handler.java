package org.framework.core;

import org.framework.bean.Context;
import org.framework.util.SetParameModelUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Enumeration;

/**
 * 映射器
 *
 * controller 要操作的对象
 * method  用来处理逻辑的方法
 *
 * */
public class Handler {
    private Object controller;
    private Method method;
    private String[] paramsName;

    public String[] getParamsName() {
        return paramsName;
    }

    public void setParamsName(String[] paramsName) {
        this.paramsName = paramsName;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
    public Handler(){

    }
    public Handler(Object controller,Method method){
        this.controller = controller;
        this.method = method;
    }
    public String  invoke(Context c ){
        if(c == null){
            return "404 erro";
        }else{
            HttpServletRequest request = c.getRequest();
            HttpServletResponse response = c.getResponse();
            //获取请求参数
            Enumeration<String> params = request.getParameterNames();
          while (params.hasMoreElements()) {
              String key = params.nextElement();
              Object value = request.getParameter(key);
              //先保存参数，有注解的别名绑定需要用到
              c.setParamsMap(key, value);
          }
                   Class<?>[] enumeration   = method.getParameterTypes();
           // ResponeBody responeBody = null;
            Object  o = null;
            try {
                Object[] objects = new Object[enumeration.length];
                objects = SetParameModelUtil.toObjectArrys(request,response,this,c.getParams(),method,c,objects);
                if(objects == null && c.getParams().size() > 0){
                    return "405 erro";
                }else if(objects == null && c.getParams().size() == 0) {
                    o = method.invoke(controller);
                }else{
                    o = method.invoke(controller,objects);
                }
            } catch (Exception e) {
                System.out.println("错误请求,找到处理类了,参数找不到之类的");
                return "500 Error Service";
            }
            return o.toString();
        }
    }
}
