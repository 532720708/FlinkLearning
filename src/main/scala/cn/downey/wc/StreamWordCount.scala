package cn.downey.wc

import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.scala._

object StreamWordCount {
  def main(args: Array[String]): Unit = {

    val params = ParameterTool.fromArgs(args)
    val host = params.get("host")
    val port = params.getInt("port")

    //创建一个流处理的执行环境
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    //接收Socket数据流
    //[atguigu@hadoop103 data]$ nc -lk 7777
    val textDataStream = env.socketTextStream(host,port)

    //逐一读取数据，扁平化之后wordcount
    val wordCountDataStream = textDataStream.flatMap(_.split("\\s"))
      .filter(_.nonEmpty)
      .map((_,1))
      .keyBy(0)
      .sum(1)

    //打印输出
    //输出前面的数字是线程编号
    wordCountDataStream.print()
//    wordCountDataStream.print().setParallelism(1)

    //执行任务
    env.execute("stream wordcount job")

  }
}
