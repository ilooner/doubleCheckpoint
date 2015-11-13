package com.datatorrent.dcheck;

import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.Operator.CheckpointListener;
import com.datatorrent.common.util.BaseOperator;
import com.google.common.collect.Sets;
import java.util.Set;

public class Consumer extends BaseOperator implements CheckpointListener
{
  private Set<Long> checkpointed = Sets.newHashSet();

  public final transient DefaultInputPort<Object> input = new DefaultInputPort<Object>() {
    @Override
    public void process(Object t)
    {
    }
  };

  @Override
  public void checkpointed(long l)
  {
    if (!checkpointed.add(l)) {
      throw new RuntimeException("Checkpointed the same window twice");
    }
  }

  @Override
  public void committed(long l)
  {
  }
}
