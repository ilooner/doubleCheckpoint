package com.datatorrent.dcheck;

import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.common.util.BaseOperator;

public class Consumer extends BaseOperator
{
  public final transient DefaultInputPort<Object> input = new DefaultInputPort<Object>() {
    @Override
    public void process(Object t)
    {
    }
  };
}
