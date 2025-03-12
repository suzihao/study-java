package com.company.spring;

import java.lang.reflect.Method;

/**
 * @author suhe17
 * @since 2025/3/12
 */
@Aspect
@Component
public class MultipleDataSourceAspect {

    private static final Logger log = LogManager.getLogger(MultipleDataSourceAspect.class);

    //定义为Controller的类将被拦截
    @Pointcut("execution(* com.jd.jdgo.report..dao.*.*(..)) ")
    public  void daoAspect() {
    }

    /**
     * 拦截目标方法，获取由@DataSource指定的数据源标识，设置到线程存储中以便切换数据源
     *
     * @param point
     * @throws Exception
     */
    @Before("daoAspect()")
    public void intercept(JoinPoint point) {
        Class<?> target = point.getTarget().getClass();
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 默认使用目标类型的注解，如果没有则使用其实现接口的注解
        for (Class<?> clazz : target.getInterfaces()) {
            resolveDataSource(clazz, signature.getMethod());
        }
        resolveDataSource(target, signature.getMethod());
    }

    /**
     * 提取目标对象方法注解和类型注解中的数据源标识
     *
     * @param clazz
     * @param method
     */
    private void resolveDataSource(Class<?> clazz, Method method) {
        try {
            Class<?>[] types = method.getParameterTypes();
            // 默认使用类型注解
            if (clazz.isAnnotationPresent(DataSource.class)) {
                DataSource source = clazz.getAnnotation(DataSource.class);
                MultipleDataSource.setDataSourceKey(source.id());
            }
            // 方法注解可以覆盖类型注解
            Method m = clazz.getMethod(method.getName(), types);
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                DataSource source = m.getAnnotation(DataSource.class);
                MultipleDataSource.setDataSourceKey(source.id());
            }
        } catch (Exception e) {
            log.error("使用拦截器配置数据DataSource出现异常",e);
        }
    }
}
