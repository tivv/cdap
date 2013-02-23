package CountCounts;

import com.continuuity.api.flow.flowlet.AbstractFlowlet;
import com.continuuity.api.flow.flowlet.InputContext;

import java.nio.charset.CharacterCodingException;

public class Incrementer extends AbstractFlowlet {
  static String keyTotal = ":sinkTotal:";

  CounterTable counters;

  public Incrementer() {
    super("Incrementer");
  }

  public void process(Integer count, InputContext context) throws CharacterCodingException {
    this.counters = getContext().getDataSet(Common.tableName);
    if (Common.debug) {
      System.out.println(this.getClass().getSimpleName() + ": Received event " + count);
    }

    if (count == null) {
      return;
    }
    String key = Integer.toString(count);
    if (Common.debug) {
      System.out.println(this.getClass().getSimpleName() + ": Emitting Increment for " + key);
    }
    // emit an increment for the number of words in this document
    this.counters.increment(key);

    if (Common.count) {
      // emit an increment for the total number of documents counted
      this.counters.increment(keyTotal);
    }
  }
}
