/**
 * Put your copyright and license info here.
 */
package com.datatorrent.dcheck;

import com.datatorrent.api.Context.OperatorContext;
import org.apache.hadoop.conf.Configuration;

import com.datatorrent.api.annotation.ApplicationAnnotation;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.DAG;
import com.datatorrent.api.DAG.Locality;
import com.datatorrent.common.util.FSStorageAgent;
import com.datatorrent.lib.io.ConsoleOutputOperator;

@ApplicationAnnotation(name="DoubleCheckpoint")
public class Application implements StreamingApplication
{

  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
    // Sample DAG with 2 operators
    // Replace this code with the DAG you want to build

    FSStorageAgent storageAgent = new FSStorageAgent("checkpoints", new Configuration());

    RandomNumberGenerator randomGenerator = dag.addOperator("randomGenerator", RandomNumberGenerator.class);
    randomGenerator.setNumTuples(500);

    Consumer consumer = dag.addOperator("consumer", new Consumer());

    dag.getOperatorMeta("consumer").getAttributes().put(OperatorContext.STORAGE_AGENT, storageAgent);
    dag.getOperatorMeta("randomGenerator").getAttributes().put(OperatorContext.STORAGE_AGENT, storageAgent);

    dag.addStream("randomData", randomGenerator.out, consumer.input);
  }
}
