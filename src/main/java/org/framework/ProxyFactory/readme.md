# 代理工厂--->自定义代理--->反射机制
1、通过反射机制获取目标对象--->clazz/method<br>
2、流程
1) 获取方法实例<br>
2) 创建代理---》通过反射执行目标方法<br>
3) ioc 注入代理而不是代理对象<br>
3) 暴露接口----> interface(Object ...)  ，<br>
这样就可以传入任意类型，任意参数，然后再按照相应顺序传入到对应的invoke 就行了<br>
4) 业务增强/返回实例对象---> ???
5) 实现切面