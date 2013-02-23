package CountRandom;

import com.continuuity.api.flow.flowlet.AbstractFlowlet;
import com.continuuity.api.flow.flowlet.OutputEmitter;

import java.util.Random;

public class RandomSource extends AbstractFlowlet {

  Random random;
  long millis = 0;
  int direction = 1;
  private OutputEmitter<Integer> randomOutput;

  public RandomSource() {
    super("RandomSource");
  }

//  @Override
//  public void configure(FlowletSpecifier specifier) {
//    TupleSchema out = new TupleSchemaBuilder().
//        add("number", Integer.class).
//        create();
//    specifier.getDefaultFlowletOutput().setSchema(out);
//    this.random = new Random(System.currentTimeMillis());
//  }

  public void generate() throws Exception {
    Integer randomNumber = new Integer(this.random.nextInt(10000));
    try {
      Thread.sleep(millis);
      millis += direction;
      if(millis > 100 || millis < 1) {
        direction = direction * -1;
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    randomOutput.emit(randomNumber);
  }
}
