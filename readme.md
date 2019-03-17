# Java基础
包含一下内容，欢迎补充

# 一：语法

## 1.1：final的小知识

其实final的作用一般java程序员都能理清楚,但是我觉得,final的String会比较特殊一点
举例子

```
     String a = "hello2";
        final String b = "hello";
        String d = "hello";
        String c = b + 2;
        String e = d + 2;
        System.out.println((a == c));  //true
        System.out.println((a == e));  //fasle
```

这段代码执行完的结果如上所示
我们知道`==`判断的是变量的引用是否相同,或者说指向的堆地址是否一样
如果咋一看代码,`c`和`e`都是在运行时使用字符串的连接,连成一个新的字符串对象,也就是说,指向的是新的堆内对象.所以至少第一个判断就不应该为true才是..
但是实际上,它的确为true.其原因是常量b在编译时期,就能确定值,编译器就会把所有b变量都替换成那个常量值,类似于c语言的宏替换.
所以实际上,编译后的结果是

```
 String a = "hello2";
        String b = "hello";
        String d = "hello";
        String c = "hello2";
        String e = d + 2;
        System.out.println(a == c);
        System.out.println(a == e);
```

运行时,也是按照这个编译后的代码去运行的
字符串变量声明所用的字符串都一样,运行时会使用在堆里面同样的字符串常量. 
以上

## 1.2：switch参数

这里只讲一点，就是switch能作把什么作为参数

告诉你，只有int

那为什么网上说，`byte`  `char`  `String` `short`  都可以作为switch参数呢？

那是因为上面的类型都可以直接转成int，而且不会丢失精度。

`String`是很特殊的一个，从jdk1.7之后，才支持作为String的参数，其实是编译器做了优化。

编译源文件后，String会用它的哈希码，也是int类型，作为switch参数

# 二：常见类知识

## 2.1：集合框架

1. 演示fill方法和reverse方法和replaceAll方法并实现一个部分替换的方法

### 2.1.1：Iterable 和Iterator

1. 有两个类

一个是`java.lang.Iterable`   意味可以迭代的

一个是`java.uitl.Iterator` 意味迭代器

这两个类不要混淆了

第一个类是基本的所有list都会实现的。

另一个是迭代器，可以实现某个容器的迭代模式

要是有某个方法的参数接受`Iterable`对象，然后惊奇的发现可以传list集合进去，别自以为形参是迭代器对象，实参就可以是list集合的，不存在的，tan90

## 2.2：反射
1. 演示最基础的方法

## 2.3：JDBC
1. 批处理
2. DML语句
3. oracle的PLSQL语句
    附上PLSQL的程序代码
    <pre>
    create or replace procedure p
    (v_a in number,v_b in number,v_sum out number,v_temp in out number)
    is
    begin
    v_sum:=v_a+v_b;
    v_temp:=v_temp+1;
    end;
    /
    </pre>
4. 不定参数
5. 可以滚蛋的结果集
6. 事务处理
7. 结果集更新

## 2.4：字节流
注1：InputStream对象的available可以返回这个字节流中可以读取的字节数  

# 三：并发编程

## 3.1线程的并发问题
具体请查看测试代码里面的com.cxh.thread包，有一个ThreadExtendsImplTest
演示的几个方面的并发问题代码
1. 演示了线程的抢占性

2. 演示了售票程序，也就是线程并发问题。
    指的一提的是，这个线程并发问题解决的还不是完美。
    因为锁的判断要消耗一定的系统资源，所以还可以优化

3. 锁的优化在设计模式中的单例设计模式中。  
    关于单例模式的饿汉式和冷汉式  
    由于线程的并发，会产生一些问题  

4. 特别说明的是，在同步代码块中（也就是被锁起来的代码中），如果线程wait了。

  那么与之相关的锁会被释放。不用担心在锁中睡眠时锁没有释放。

5. 顺便讲一下并发线程的锁类型。一般情况下，Synchronizer代码块里面需要自己设置锁对象

   其他的，成员方法加锁的话，锁对象是this。

   静态方法加锁的话，锁对象是Class对象

   以上，不想为这个知识点码代码了，各位记一下，脑子里有一个映像就行

 6. 特别说明，如果要执行notify或者signal的代码，必须在同步代码块里面执行。
     否则抛一个java.lang.IllegalMonitorStateException异常你都不知道怎么肥事。



# 四：深入理解

## 4.1引用面面观

你可能只听说过引用，却不知道引用其实分为四类

以下介绍这四种引用

1. 强引用 ： 这个引用是我们经常用的，形如`String a=xxx` 或者`Integer i =1`;

   都是强引用(`Strong Reference`)

   这些引用在垃圾收集时，都不会被回收，即使内存不足

2. 弱软用(`Soft Reference`)，这个需要特殊的写法才能实现

   `SoftReference sr=new SoftReference(new Person());`

   这个代码建立的就是弱引用，稍次于强引用,这句代码建立的`person`对象，是可以被垃圾收集器回收的

   只不过只有在堆内存空间不够的情况下，才会回收。

3. 弱引用(`Weak Reference`)  需要这么实现

   `WeakReference wr=new WeakReference(new Person());`

   在垃圾收集时，只要上面那句代码创建的`person`对象被发现了，立马收集，不留余地

   >我觉得用这种引用来设计缓存是可以的，因为不像软引用，它必须得内存不够了才会回收。
   >
   >这样会导致GC的时刻的提前到来。
   >
   >弱引用它随时随地都可以回收，所以不会积攒对象到内存不足

4. 虚引用`Phantom Reference`  其实也跟弱引用一样，在垃圾收集时，随时可被回收

   但是还是有区别，虚引用在使用时必须和引用队列联合使用。以便在虚引用所引用的对象被垃圾回收时

   在程序中，可以收到通知

## 4.2 基本类型的一些有趣的地方

`byte、char、short`  这些类型自己相加后得到的类型，是`int`类型

至于为什么是这样子的，是因为对于这些类型，jvm虚拟机里面并没有对应的指令去运算。

实际上用的是int的指令去运算的，也就是它们在运行时会转成int去计算，当然得到的结果也就是int了

> 可以强制类型转换，不过不建议，会丢失精度



## 4.3:JDK7的comprae方法

1. JDK1.7升级后，对comprae方法做出了新的限制，该限制如果不被满足，在使用Sort将会抛出一个异常

   ```
   Comparison method violates its general contract
   ```

   解决办法有两个，一个就是加个`-Djava.util.Arrays.useLegacyMergeSort=true`

一个就是修改你的代码，符合新的要求

为什么会报错呢？

究其原因，是因为JDK1.7 后，Sort方法内部使用的算法改成了TimeSort

这个算法在使用不科学的排序时，就会抛如上的异常。

# 五：Java规范

1. 根据 Java 规范，使用 equal() 方法来判断两个相等的对象，必须具有相同的 hashcode。




# 六：SSLSocket通信
首先认识下SSL协议吧

ssl协议的基本思路是采用公钥加密法，也就是说，客户端先向服务端请求公钥，然后用这个公钥加密信息。发给服务端，服务端再用密钥解密信息。
>  但是这样子有个问题，就是客户端不知道服务端发来的公钥是不是冒充的，所以一般来说，都会把公钥放在证书里面，只要证书是可信的，那么密钥就是可信的
>
>  然后其实客户端用公钥加密信息其他耗费的计算量会很大，那么如何减少耗用的时间
>
>  解决办法是每一次对话都生成一个会话密钥`session key`用它来加密信息，由于这个密钥是对称密钥，所以花费的时间会更小，然后公钥用来加密`session key`就行了

SSL的协议的基本流程是酱紫的

1. 客户端向服务端索要证书，获取公钥。
2. 双发协商生成一个对话密钥
3. 双方采用对话密钥进行通信

前两步，又称握手阶段

## 6.1：SSLSocket的详细过程

1. 客户端向服务端发起加密通信的请求，里面包含以下信息

   1. 我支持什么协议版本，比如说`TLS 1.0`版
   2. 一个客户端生成的随机数，**random1**
   3. 支持的加密方法，比如说`RSA`公钥加密
   4. 支持的压缩方法

2. 服务端接受客户端发来的信息，进行解析，然后发送响应给客户端。里面包含

   1. 确认协议版本。

      > 如果服务端不支持客户端的协议版本，加密过程结束

   2. 确认加密方法

   3. 服务端生成的随机数**random2**

   4. 服务端证书

      > 如果服务端需要客户端的身份验证，会在这一步要求客户端提供证书

3. 客户端响应

   客户端接受来自服务端的响应后，会校验服务端证书的有效性

   > 如果证书不是可信机构颁布、或者证书中的域名与实际域名不一致、或者证书已经过期，就会向访问者显示一个警告，由其选择是否还要继续通信 

   有效性通过后，客户端会从证书里面提取公钥，并向服务端发送下列三项信息

   1. 一个随机数。该随机数由公钥加密，防止窃听

      > 该随机数是第三个随机数了，也被称为`pre-master key `

   2. `编码改变通知，表示随后的信息都将用双方商定的加密方法和密钥发送。 `

   3. 客户端握手结束通知。

4. 服务端响应--服务端接受来自客户端的信息后，发回响应给客户端，包含

   1. 握手结束通知
   2. `编码改变通知，表示随后的信息都将用双方商定的加密方法和密钥发送。 `

客户端和服务端使用前面通讯获知的三个随机数，生成对话密钥，来进行下面的对话加密传输

> 注意点：前两次握手通讯都是明文传输的，所以窃听者可以看到前两次握手加密传的随机数。
>
> 整个加密传输是否安全，取决于第三次握手传输的随机数能不能被破解。

## 6.2：SSLSocket常用类介绍
1. `SSLSocketFactory`  这是一个工厂类，可以用于生成`SSLSocket `

   听名字就知道它是一个工厂类，用于产出对象的。

   想要获取`SSLSocketFactory` 可以通过`SSLSocketFactory.getDefault(); `获取

2. `SSLSocket`这个类继承自Socket类又提供了安全套接字。

   可以构造一个带有端口和IP的`SSLSocket`。

   主要可以通过`SSLSocketFactory`对象 的以下方法创建

   `*createSocket(String host, int port)* `

   `*createSocket(String host, int port, InetAddress clientHost, int clientPort)* `

   `*createSocket(InetAddress host, int port)* `

   `*createSocket(InetAddress host, int port, InetAddress clientHost, int clientPort)* `

   `*createSocket(Socket socket, String host, int port, boolean autoClose)* `

3. `SSLServerSocketFactory` 这个类跟`SSLSocketFactory`  大致功能相同，只不过执行

   `createServerSocket`拿到的是`SSLServerSocket`实例。

   用于构建服务端的安全套接字

4. `SSLServerSocket`这是一个服务端的安全套接字，类似于`SSLSocket`

## 6.3：SSL工作方式

有两种：

1. SSL连接不需要客户端验证，这个时候，与SSL服务端连接的客户端是什么都可以。阿猫阿狗也行。

   但是客户端和服务端连接是加密的，通过服务端的证书来加密

   这种情况下的工作方式是酱紫的

   1. 服务端为客户端提供证书，客户端验证服务端是否是合法的。

   2. 客户端生成`premaster secret `并使用服务端的公钥进行加密，发给服务端、

      公钥包含在服务端的证书中

   3. 服务端解密客户端发来的`premaster secret `

   4. 服务端和客户端通过`premaster secret `生成` master secret `,接着从` master secret `生成

      ` session keys `,这个是一个对称密钥，用于在会话过程中加密和解密信息并保持信息的完整性

   5. 在这个过程之后，客户端和服务端就就可以用`session keys`来进行加密通讯了

2. SSL连接需要客户端验证身份，这种情况下

   客户端需要发送证书给服务端，以便相互验证连接。

   除了客户端的证书验证外，其他工作流程没有任何改变，这种情况下，通讯客户端的用户名和密码验证变得多余

## 6.4: 专有名称解释

1. `jks` 是密钥库，里面存储的是密钥和证书.

   可以通过`keytool -genkeypair -alias myserver -keystore myserver.jks `生成密钥库

   这个命令行就是生成了一个名叫myserver.jks的密钥库，里面有一个密钥对(公钥和密钥)

   公钥包装在证书中，而证书和密钥存储在别名(myserver)命名的密钥库中

2. `csr  `即证书请求文件，可以把`csr`证书送给专门的CA认证去签名，就可以得到一个证书公钥文件

   `csr`证书文件可以通过这个命令生成

   `keytool -certreq -file test_server.csr -alias TEST_SERVER -keystore test_server.jks `

   这个命令行的作用就是从`test_server.jks `密钥库中，将`TEST_SERVER `对应的证书信息导出为证书请求文件。

3. `cer `这个就是证书文件了，可以通过证书请求文件由CA签名得到，也可以直接从密钥库中导出

   第一种方式，用第二步拿到的证书文件，用自己的密钥签名

   > 所谓自己的密钥，其实就是通过genkeypair 生成的

   签名的命令行是这个

   `keytool -gencert -infile test_server.csr -outfile test_server.cer -alias TEST_ROOT -keystore TEST_ROOT.jks `

   意思就是用`TEST_ROOT.jks `里面`TEST_ROOT`的密钥，来对`test_server.csr`证书请求文件进行签名，生成已签名的证书文件。保存在`test_server.cer`文件里面。



   第二种方式，就是从jks，密钥库中导出证书，命令行是这个

   `keytool -exportcert -alias TEST_ROOT -file test_root.cer -keystore test_root.jks `

   意思是从jks文件中读取`TEST_ROOT `关联的证书文件，并存储在`test_root.cer`文件中

4. 顺便介绍几个命令

   `keytool -importcert -alias TEST_ROOT -file test_root.cer -keystore TEST_SERVER.jks `

   该命令的作用是将证书导入到jks文件中，前提是jks文件不含`TEST_ROOT `别名的条目，否则就是更新证书，而不是导入证书了。



   `keytool – printcert – v – file test_server.cer `

   打印证书信息



   `keytool – list – v – keystore test_server.jks `

   显示jks文件的条目信息

5. 证书其实是以证书链的形式组织的，一个证书，在他变成证书之前需要被中级证书签署，而中级证书又要被根证书签署。这样就构成了一条证书链`根证书--> 中级证书---> SSL证书`只有当整个证书链的证书都是可信时，整个证书验证的结果才是可信。

6. 制作`TrustStore`即信任证书存储库

   假设你现在有一个证书，想用这个证书制作一个信任证书列表。可以酱紫做

   `keytool -import -alias serverkey -file server.crt -keystore tclient.keystore`

   这样就是把server.crt证书导入到tclient.keystore,信任证书列表了














