package main;

import bean.TestBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        TestBean testBean = (TestBean) ctx.getBean("testBean");

        String spelStr ="#myLogUtil.test()";//返回随机数乘以10的值

        ExpressionParser expParser = new SpelExpressionParser();

        StandardEvaluationContext context = new StandardEvaluationContext(testBean);

        context.setVariable("myLogUtil",testBean);
        Expression exp = expParser.parseExpression(spelStr);

        String result = exp.getValue(context,String.class);


        System.out.println(result);
    }

    public static String fun(String x) {
        System.out.println("foo");
        return x;
    }
}
