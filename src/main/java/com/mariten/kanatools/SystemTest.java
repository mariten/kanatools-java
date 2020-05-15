/**
 * Licensed to Ravel, Inc. under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  Ravel, Inc. licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.goldenorb;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.Server;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.JobID;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.TaskAttemptID;
import org.apache.hadoop.mapreduce.TaskID;
import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.ReflectionUtils;
import org.apache.zookeeper.ZooKeeper;
import org.goldenorb.conf.OrbConfiguration;
import org.goldenorb.event.OrbCallback;
import org.goldenorb.event.OrbEvent;
import org.goldenorb.event.OrbExceptionEvent;
import org.goldenorb.io.InputSplitAllocator;
import org.goldenorb.io.input.RawSplit;
import org.goldenorb.io.input.VertexBuilder;
import org.goldenorb.io.output.OrbContext;
import org.goldenorb.io.output.VertexWriter;
import org.goldenorb.jet.SystemTestMember;
import org.goldenorb.net.OrbDNS;
import org.goldenorb.queue.InboundMessageQueue;
import org.goldenorb.queue.OutboundMessageQueue;
import org.goldenorb.queue.OutboundVertexQueue;
import org.goldenorb.zookeeper.AllDoneBarrier;
import org.goldenorb.zookeeper.Barrier;
import org.goldenorb.zookeeper.LeaderGroup;
import org.goldenorb.zookeeper.OrbFastAllDoneBarrier;
import org.goldenorb.zookeeper.OrbFastBarrier;
import org.goldenorb.zookeeper.OrbZKFailure;
import org.goldenorb.zookeeper.ZookeeperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SystemTest, spawned from via {@link SystemTestProcess}, is responsible for loading input data,
 * assigning file splits to other {@link SystemTest} processes and coordinating with other
 * {@link SystemTest} processes via the exchange of {@link Messages} and {@link Vertices}. In addition to
 * start up and coordination, {@link SystemTest} processes are run responsible for stepping through the
 * graph algorithms themselves, via the compute method.
 */
public class SystemTest {
 
  public SystemTest() {
 System.out.println("");
 System.out.println("");
 System.out.println("");
 System.out.println("");
 System.out.println("");
 System.out.println("");
 System.out.println("");
 System.out.println("");
 System.out.println("");
 System.out.println("");
 System.out.println("");
 System.out.println("");
 System.out.println("");
 System.out.println("");
 System.out.println("");
 System.out.println("");
 System.out.println("");
 System.out.println("");
 System.out.println("");
 System.out.println("");
 System.out.println("");
 System.out.println("");
 System.out.println("");
 System.out.println("");
 System.out.println("");    
 System.out.println("");    
 System.out.println("");    
 System.out.println("");    
 System.out.println("");    
 System.out.println("");    
 System.out.println("");    
 System.out.println("");    
 System.out.println("");    
 System.out.println("");    
 System.out.println("");    
 System.out.println("");    
 System.out.println("");    
 System.out.println("");    
 System.out.println("");    
 System.out.println("");    
 System.out.println("");    
 System.out.println("");    
 System.out.println("");    
 System.out.println("");    
 System.out.println("");    
  }
}
