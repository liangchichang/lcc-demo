package com.lcc.demo.thread.concurrent.multithread;

import com.lcc.demo.thread.Utils.LccThreadPool;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Lcc
 * @version 2019/1/15
 *
 * volatile禁止指令重排实验（实践验证失败，无法重现指令重排下的bug）.
 */
public class VolatileTest4 implements Runnable {

  private static VolatileTest4 singleTonInstance = null;

  private int i = 0;

  private VolatileTest4() {
  }

  private VolatileTest4 getSingleTonInstance() {
    if (singleTonInstance == null) {
      synchronized ("1") {
        if (singleTonInstance == null) {
          singleTonInstance = new VolatileTest4();
        }
      }
    }
    return singleTonInstance;
  }

  private void test() {
    System.out.println(this.toString());
  }

  @Override
  public void run() {
    VolatileTest4 instance = getSingleTonInstance();
    instance.test();
  }

  public static void main(String[] args) {
    ThreadPoolExecutor theadPool = LccThreadPool.getTheadPool();
    for (int i = 0; i < 100; i++) {
      theadPool.execute(new VolatileTest4());
    }
    System.exit(1);
  }

  public int getI() {
    return i;
  }

  public void setI(int i) {
    this.i = i;
  }

  @Override
  public String toString() {
    return "VolatileTest4{" +
        "i=" + i +
        '}';
  }
}

//C:\Users\lcc>javap -p -v D:\project_workspace\lcc_demo\test\target\classes\com\lcc\demo\thread\concurrent\multithread\VolatileTest4.class
//Classfile /D:/project_workspace/lcc_demo/test/target/classes/com/lcc/demo/thread/concurrent/multithread/VolatileTest4.class
//Last modified 2019-1-15; size 742 bytes
//    MD5 checksum 93758e055b65b84081e6e5831fe6fb15
//    Compiled from "VolatileTest4.java"
//public class com.lcc.demo.thread.concurrent.multithread.VolatileTest4
//    minor version: 0
//    major version: 52
//    flags: ACC_PUBLIC, ACC_SUPER
//    Constant pool:
//    #1 = Methodref          #6.#23         // java/lang/Object."<init>":()V
//    #2 = Fieldref           #4.#24         // com/lcc/demo/thread/concurrent/multithread/VolatileTest4.singleTonInstance:Lcom/lcc/demo/thread/concurrent/multithread/VolatileTest4;
//    #3 = String             #25            // 1
//    #4 = Class              #26            // com/lcc/demo/thread/concurrent/multithread/VolatileTest4
//    #5 = Methodref          #4.#23         // com/lcc/demo/thread/concurrent/multithread/VolatileTest4."<init>":()V
//    #6 = Class              #27            // java/lang/Object
//    #7 = Utf8               singleTonInstance
//    #8 = Utf8               Lcom/lcc/demo/thread/concurrent/multithread/VolatileTest4;
//    #9 = Utf8               <init>
//  #10 = Utf8               ()V
//      #11 = Utf8               Code
//      #12 = Utf8               LineNumberTable
//      #13 = Utf8               LocalVariableTable
//      #14 = Utf8               this
//      #15 = Utf8               getSingleTonInstance
//      #16 = Utf8               ()Lcom/lcc/demo/thread/concurrent/multithread/VolatileTest4;
//      #17 = Utf8               StackMapTable
//      #18 = Class              #27            // java/lang/Object
//      #19 = Class              #28            // java/lang/Throwable
//      #20 = Utf8               <clinit>
//  #21 = Utf8               SourceFile
//      #22 = Utf8               VolatileTest4.java
//      #23 = NameAndType        #9:#10         // "<init>":()V
//      #24 = NameAndType        #7:#8          // singleTonInstance:Lcom/lcc/demo/thread/concurrent/multithread/VolatileTest4;
//      #25 = Utf8               1
//      #26 = Utf8               com/lcc/demo/thread/concurrent/multithread/VolatileTest4
//      #27 = Utf8               java/lang/Object
//      #28 = Utf8               java/lang/Throwable
//      {
//private static volatile com.lcc.demo.thread.concurrent.multithread.VolatileTest4 singleTonInstance;
//    descriptor: Lcom/lcc/demo/thread/concurrent/multithread/VolatileTest4;
//    flags: ACC_PRIVATE, ACC_STATIC, ACC_VOLATILE
//
//private com.lcc.demo.thread.concurrent.multithread.VolatileTest4();
//    descriptor: ()V
//    flags: ACC_PRIVATE
//    Code:
//    stack=1, locals=1, args_size=1
//    0: aload_0
//    1: invokespecial #1                  // Method java/lang/Object."<init>":()V
//    4: return
//    LineNumberTable:
//    line 11: 0
//    line 12: 4
//    LocalVariableTable:
//    Start  Length  Slot  Name   Signature
//    0       5     0  this   Lcom/lcc/demo/thread/concurrent/multithread/VolatileTest4;
//
//public com.lcc.demo.thread.concurrent.multithread.VolatileTest4 getSingleTonInstance();
//    descriptor: ()Lcom/lcc/demo/thread/concurrent/multithread/VolatileTest4;
//    flags: ACC_PUBLIC
//    Code:
//    stack=2, locals=3, args_size=1
//    0: getstatic     #2                  // Field singleTonInstance:Lcom/lcc/demo/thread/concurrent/multithread/VolatileTest4;
//    3: ifnonnull     37
//    6: ldc           #3                  // String 1
//    8: dup
//    9: astore_1
//    10: monitorenter
//    11: getstatic     #2                  // Field singleTonInstance:Lcom/lcc/demo/thread/concurrent/multithread/VolatileTest4;
//    14: ifnonnull     27
//    创建并初始化一个VolatileTest4类型的对象
//    17: new           #4                  // class com/lcc/demo/thread/concurrent/multithread/VolatileTest4
//    复制引用
//    20: dup
//    调用VolatileTest4的构造方法
//    21: invokespecial #5                  // Method "<init>":()V
//    将静态引用指向对象
//    24: putstatic     #2                  // Field singleTonInstance:Lcom/lcc/demo/thread/concurrent/multithread/VolatileTest4;
//    27: aload_1
//    28: monitorexit
//    29: goto          37
//    32: astore_2
//    33: aload_1
//    34: monitorexit
//    35: aload_2
//    36: athrow
//    37: getstatic     #2                  // Field singleTonInstance:Lcom/lcc/demo/thread/concurrent/multithread/VolatileTest4;
//    40: areturn
//    Exception table:
//    from    to  target type
//    11    29    32   any
//    32    35    32   any
//    LineNumberTable:
//    line 15: 0
//    line 16: 6
//    line 17: 11
//    line 18: 17
//    line 20: 27
//    line 22: 37
//    LocalVariableTable:
//    Start  Length  Slot  Name   Signature
//    0      41     0  this   Lcom/lcc/demo/thread/concurrent/multithread/VolatileTest4;
//    StackMapTable: number_of_entries = 3
//    frame_type = 252 /* append */
//    offset_delta = 27
//    locals = [ class java/lang/Object ]
//    frame_type = 68 /* same_locals_1_stack_item */
//    stack = [ class java/lang/Throwable ]
//    frame_type = 250 /* chop */
//    offset_delta = 4
//
//static {};
//    descriptor: ()V
//    flags: ACC_STATIC
//    Code:
//    stack=1, locals=0, args_size=0
//    0: aconst_null
//    1: putstatic     #2                  // Field singleTonInstance:Lcom/lcc/demo/thread/concurrent/multithread/VolatileTest4;
//    4: return
//    LineNumberTable:
//    line 9: 0
//    }
//    SourceFile: "VolatileTest4.java"