package ml.wonwoo.springexpressionlanguage;

import org.junit.Test;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MapFormatTests {


  @Test
  public void message() {
    String message = "my foo is #{name}, i bar #{age}";
    SpelExpressionParser parser = new SpelExpressionParser();
    Map<String,String> map = new HashMap<>();
    map.put("name", "wonwoo");
    map.put("age", "33");
    StandardEvaluationContext context = new StandardEvaluationContext(map);
    context.addPropertyAccessor(new MapAccessor());
    Expression expression = parser.parseExpression(message, ParserContext.TEMPLATE_EXPRESSION);
    String value = expression.getValue(context, String.class);
    assertThat(value).isEqualTo("my foo is wonwoo, i bar 33");
  }

}
