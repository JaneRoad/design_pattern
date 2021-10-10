

# 六大原则

- 单一职责

**一个类和方法只做一件事**

- 里氏替换

**子类继承父类尽量别重写父类方法，增加自身功能就好**

- 依赖倒置

**细节依赖抽象、下层依赖上层**

- 接口隔离

**接口尽量设计细化，不要太多方法**

- 迪米特原则

**类与类之间关联系减小，耦合度降低**

- 开闭原则

**如果需要代码迭代，尽量扩展，而不是在原来的基础修改**





# 分类

## 创建型

这类模式提供创建对象的机制， 能够提升已有代码的灵活性和可复用性。

![](https://gitee.com/janeroad/iamge-cloud/raw/master/NoteImage/image-20211006172656546.png)







## 结构型

这类模式介绍如何将对象和类组装成较大的结构， 并同时保持结构的灵活和高效。

![](https://gitee.com/janeroad/iamge-cloud/raw/master/NoteImage/image-20211006172745098.png)





## 行为型

这类模式负责对象间的高效沟通和职责委派。

![](https://gitee.com/janeroad/iamge-cloud/raw/master/NoteImage/image-20211006172957706.png)







# 常用设计模式



## 策略模式

### 简介

策略模式是一种行为模式，也是替代⼤量 if-else 的利器。它所能帮你解决的是场景，一般是具有同类可替代的行为逻辑算法场景。比如不同类型的交易方式(信⽤卡、⽀付宝、微信)、生成唯一ID策略 (UUID、DB⾃增、DB+Redis、雪花算法、Leaf算法)等，都可以使用策略模式行为包装，供给外部使用。



### 举例

接下来，做一个两个数各种计算的功能

**1、定义一个计算的接口**

~~~java
public interface Operation {
    public Integer doOperation(Integer num1, Integer num2);
}
~~~

**2、简单写两个方法，加法和减法实现doOperation方法**

~~~java
public class Add implements Operation{
    @Override
    public Integer doOperation(Integer num1, Integer num2) {
        return num1+num2;
    }
}
~~~



~~~java
public class Sub implements Operation{
    @Override
    public Integer doOperation(Integer num1, Integer num2) {
        return num1-num2;
    }
}
~~~



**3、定义一个计算器类**

引入计算的接口Operation，然后构造方法中的入参为Operation，使得入参可以是实现了Operation的对象，比如Add类的实例化对象，然后doOperation就可以正确的让两个数执行相应的操作（加或者减）

~~~java
public class Calculator {
    private Operation operation;

    public Calculator(Operation operation){
        this.operation = operation;
    }

    public Integer doOperation(Integer num1, Integer num2){
        return this.operation.doOperation(num1, num2);
    }
}
~~~



**4、测试类进行测试**

我们首先实例化add，然后用add对象实例化出一个calculator计算器（加法），用calculator的doOperation方法，就能够执行1+2。

同理，如果我们想实现减法，只要实例化一个减法的计算器出来就可以了。

~~~java
public class Test {
    public static void main(String[] args) {
        Add add = new Add();
        Calculator calculator = new Calculator(add);
        System.out.println(calculator.doOperation(1, 2));
    }
}
~~~



上述就是一个策略模式的小demo，



### 总结

策略模式可以把方法中的if语句优化掉，让代码更好维护，上述例子中，如果计算器要增加除法、乘法或者更多高级操作，只要扩展续写实现Operation接口的类就行了，而在实际使用中传入Calculator的构造方法就行，**这样的代码具有隔离性和扩展性**。









## 装饰模式

### 简介

装饰器的核心就是在不改原有类的基础上给类新增功能。不改变原有类，有的同学会想到继承、AOP切面，当然这些方式都可以实现，但是使用装饰器模式会是另外一种思路更为灵活，**可以避免继承导致的⼦类过多，也可以避免AOP带来的复杂性**。

在我们熟悉的场景很多都用到了装饰器器模式，比如`new BufferedReader(new FileReader("")); `，这段代码学习到字节流、字符流、⽂件流的内容时都见到了这样的代码，一层嵌套一层，一层嵌套一层，字节流转字符流等等，这样⽅式的使用就是装饰器模式的一种体现。





### 举例

举一个买衣服穿的例子。

**1、定义Person接口**

当中定义两个方法，cost()表示花钱，show()表示穿衣服

~~~java
public interface Person {
    public Double cost();
    public void show();
}
~~~

**2、定义抽象类ClothesDecorator 衣服装饰器**

这个抽象类实现Person接口。在构造方法中定义入参为Person接口（实现了Person接口的类）

~~~java
public abstract class ClothesDecorator implements Person{
    protected Person person;
    protected ClothesDecorator(Person person) {
        this.person = person;
    }
}
~~~

**3、定义Shirt类，继承ClothesDecorator衣服装饰器**

由于ClothesDecorator是抽象类并且实现Person接口，这意味着继承了它的Shirt类必须要实现cost和show方法

Java中每当创建子类的实例时，父类的实例被隐式创建

所以Shirt中的构造方法用`super()`可以调用父类构造函数

在`cost()`方法中使用`this.person.cost()`再加上shirt的1000可以达到累加消费金额的效果

~~~java
public class Shirt extends ClothesDecorator{
    protected Shirt(Person person) {
        super(person);
    }

    @Override
    public Double cost() {
        return this.person.cost()+1000;
    }

    @Override
    public void show() {
        this.person.show();
        System.out.println("买了一件衬衫，消费"+this.cost());
    }
}
~~~

**4、定义Trousers类与Shirt类同理**

~~~java
public class Trousers extends ClothesDecorator{
    protected Trousers(Person person) {
        super(person);
    }

    @Override
    public Double cost() {
        return this.person.cost()+800;
    }

    @Override
    public void show() {
        this.person.show();
        System.out.println("买了一条裤子，消费"+this.cost());
    }
}
~~~



**5、定义一个XiaoMing类**

实现Person接口，初始化方法

```java
public class XiaoMing implements Person{
    @Override
    public Double cost() {
        return 0.0;
    }

    @Override
    public void show() {
        System.out.println("没穿衣服");
    }
}
```



**6、测试类进行测试**

```java
public class Test {
    public static void main(String[] args) {
        Person xiaoMing = new XiaoMing();
        xiaoMing = new Shirt(xiaoMing);
        xiaoMing = new Trousers(xiaoMing);
        xiaoMing.show();
    }
}
```

![](https://gitee.com/janeroad/iamge-cloud/raw/master/NoteImage/image-20211010174654373.png)

可以看到`xiaoming`对象被实例化出来后，放到`Shirt()`和`Trousers()`方法中可以达到装饰效果









### 总结

- 使用装饰器模式满足单⼀职责原则，可以在⾃己的装饰类中完成功能逻辑的扩展，而不影响主类，同时可以按需在运行时添加和删除这部分逻辑。装饰器模式与继承父类重写方法，在某些时候需要按需选择，并不一定某一个就是最好。
- 装饰器实现的重点是对抽象类继承接口⽅式的使用，同时设定被继承的接口可以通过构造函数传递其实现类，由此增加扩展性并重写方法里可以实现此部分父类实现的功能。
- 装饰器模式就像夏天热穿短裤，冬天冷穿棉裤，⾬天穿雨衣一样，你的本身没有被改变，而你的需求被不不同的装饰⽽实现。





## 观察者模式

### 简介

观察者模式是当一个⾏为发生时传递信息给另外⼀个⽤户接收做出相应的处理，两者之间没有直接的耦合关联。

### 举例

**1、定义抽象类Customer代表客户**

```java
public abstract class Customer {
    public abstract void update();
}
```

**2、定义类CustomerA实现客户类**

update用来简单表示送报的观察者收到指令后送报纸给客户

```java
public class CustomerA extends Customer {
    @Override
    public void update() {
        System.out.println("客户A的报纸已送达");
    }
}
```

**3、定义类CustomerB实现客户类**

```java
public class CustomerB extends Customer{
    @Override
    public void update() {
        System.out.println("客户B的报纸已送达");
    }
}
```

**4、定义类NewspaperOffice表示报社**

addCustomer用来添加客户，notifyAllObservers用来提醒所有的送报观察者送报（调用各个客户的update方法让他们收到报纸）

```java
public class NewspaperOffice {
    private List<Customer> customers= new ArrayList<>();

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void notifyAllObservers(){
        for (Customer customer : customers) {
            customer.update();
        }
    }

    public void newspaper(){
        this.notifyAllObservers();
    }
}
```

**5、测试类测试**

~~~java
public class Test {
    public static void main(String[] args) {
        NewspaperOffice newspaperOffice = new NewspaperOffice();
        Customer customerA = new CustomerA();
        Customer customerB = new CustomerB();
        newspaperOffice.addCustomer(customerA);
        newspaperOffice.addCustomer(customerB);
        newspaperOffice.newspaper();
    }
}
~~~

运行结果：

![](https://gitee.com/janeroad/iamge-cloud/raw/master/NoteImage/image-20211010175755013.png)



### 总结

观察者从结构上满足开闭原则，当需要增加其他的监听事件（例子中的notifyAllObservers方法）或者修改监听逻辑（比如那些客户今天不送包，就不调用update方法）是不需要改动事件处理类的





## 单例模式

### 简介

单例模式是最简单的设计模式之一，我们在开发中经常会遇到需要保证一个类只有一个实例哪怕多线程同时访问，并需要提供一个全局访问此实例的点。

### 举例

**1、定义Single类，将唯一的一个实例化对象instance设置为全局对象**

别的对象无法用new实例化对象，只能用getInstance获取对象，当第一次实例化Single的时候，输出`创建了Single对象`，当后面来的线程创建对象的时候只返回已经实例化过的全局对象

```java
public class Single {
    private volatile static Single instance;

    private Single(){
        System.out.println("创建了Single对象");
    }

    public static synchronized Single getInstance(){
        if (Objects.isNull(instance)){
            synchronized (Single.class){
                instance = new Single();
            }
        }
        return instance;
    }
}
```



**有人可能会问了为什么instance 要加上volatile，为什么`getInstance`和` instance = new Single();`要加上synchronized？**

这是因为在实现单例模式时，如果未考虑多线程的情况，就容易写出下面的错误代码

~~~java
public class Singleton {
    private static Singleton instance;
  
    private Singleton() {
    }
  
    public Singleton getInstance() {
        if (Objects.isNull(instance)){
            instance = new Single();
        }
        return instance;
    }
}
~~~

在多线程的情况下，这样写可能会导致`instance`有多个实例。比如下面这种情况，考虑有两个线程同时调用`getInstance()`：

| Time | Thread A             | Thread B             |
| ---- | -------------------- | -------------------- |
| T1   | 检查到`instance`为空 |                      |
| T2   |                      | 检查到`instance`为空 |
| T3   |                      | 初始化对象`A`        |
| T4   |                      | 返回对象`A`          |
| T5   | 初始化对象`B`        |                      |
| T6   | 返回对象`B`          |                      |

可以看到，`instance`被实例化了两次并且被不同对象持有。完全违背了单例的初衷。

所以我们需要加锁，**但是又会有人要问了，直接在`getInstance()`加synchronized锁不就完事了，搞那么多synchronized干嘛？**

如果我们直接在`getInstance()`加synchronized锁确实可以解决问题，但是用到了`synchronized`，会导致很大的性能开销，并且加锁其实只需要在第一次初始化的时候用到，之后的调用都没必要再进行加锁。

所以这个时候我们需要用到双重检查锁，双重检查锁就是先判断对象是否已经被初始化，再决定要不要加锁。在instance已经被实例化后，会判断instance非空，直接返回对象而不会获得锁，这样，除了初始化的时候会出现加锁的情况，后续的所有调用都会避免加锁而直接返回，解决了性能消耗的问题。



**这个时候，肯定又有人要问了，那修饰instance是干嘛用的？**

这是因为上述实现还存在隐患，实例化对象的那行代码实际上可以分解成以下三个步骤：

1. 分配内存空间
2. 初始化对象
3. 将对象指向刚分配的内存空间

但是有些编译器为了性能的原因，可能会将第二步和第三步进行**重排序**，顺序就成了：

1. 分配内存空间
2. 将对象指向刚分配的内存空间
3. 初始化对象

现在考虑重排序后，两个线程发生了以下调用：

| Time | Thread A                 | Thread B                                 |
| ---- | ------------------------ | ---------------------------------------- |
| T1   | 检查到`instance`为空     |                                          |
| T2   | 获取锁                   |                                          |
| T3   | 再次检查到`instance`为空 |                                          |
| T4   | 为`instance`分配内存空间 |                                          |
| T5   | 将`instance`指向内存空间 |                                          |
| T6   |                          | 检查到`instance`不为空                   |
| T7   |                          | 访问`instance`（此时对象还未完成初始化） |
| T8   | 初始化`instance`         |                                          |

在这种情况下，T7时刻线程B对`instance`的访问，访问的是一个**初始化未完成**的对象。

所以我才在instance上加了一个volatile的修饰，使用了volatile关键字后，重排序被禁止，所有的写（write）操作都将发生在读（read）操作之前。



**2、测试类测试**

```java
public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                Single.getInstance();
            }).start();

        }
    }
}
```

运行上述代码可以发现，多线程场景下也只会输出一次`创建了Single对象`，这意味着这个单例的类成功的实现了。

### 总结

单例模式的实现分为很多种，比如**线程不安全和安全的两种版本的懒汉模式**、**饿汉模式**（线程安全）、**双重锁检验**（线程安全）、**CAS**（线程安全）

懒汉、恶汉区别就在于，恶汉是一开始就初始化好instance对象，只要有人调用getInstance方法就给他对象，而懒汉是判断是否已经有了，没有先实例化再给，已经有了就直接给。其他的版本都是在实现线程安全上做了不同的方案。



## 工厂模式

### 简介

工厂模式是一种创建型设计模式，在父类中提供一个创建对象的方法，允许子类决定实例化对象的类型。

### 举例

**1、定义电脑接口**

```java
public interface Computer {
}
```

**2、定义苹果电脑类实现电脑接口**

```java
public class Apple implements Computer{
    public Apple(){
        System.out.println("生产苹果电脑");
    }
}
```

**3、定义戴尔电脑类实现电脑接口**

```java
public class Dell implements Computer{
    public Dell(){
        System.out.println("生产戴尔电脑");
    }
}
```

**4、定义电脑工厂类**

```java
public class ComputerFactory {
    public Computer createComputer(String name){
        Computer computer = null;
        if ("Apple".equals(name)){
            computer = new Apple();
        }
        if ("Dell".equals(name)){
            computer = new Dell();
        }
        return computer;
    }
}
```

**5、测试类测试**

```java
public class Test {
    public static void main(String[] args) {
        ComputerFactory factory = new ComputerFactory();
        Computer macbook = factory.createComputer("Apple");
        Computer dellComputer = factory.createComputer("Dell");
    }
}
```

![](https://gitee.com/janeroad/iamge-cloud/raw/master/NoteImage/image-20211010183234606.png)

### 总结

工厂模式并不复杂，它的优点是

- 避免创建者与具体的产品逻辑耦合
- 满足单一职责
- 每一个业务逻辑都在所属自己的类中完成
- 满足开闭原则
- 不需要更改调用方就可以在程序中引入新的类型





## 适配器模式

### 简介

适配器模式的主要作用就是把原本不兼容的接口通过适配器修改做到统一。就像我们生活中的万能充、数据线、拓展坞等等。

### 举例

**1、定义MusicPlayer音乐播放器接口**

```java
public interface MusicPlayer {
    public void play(String type,String filename);
}
```

**2、定义我的播放器类MyPlayer**

```java
public class MyPlayer {
    public void playMp3(String filename){
        System.out.println("play mp3:"+filename);
    }

    public void playWma(String filename){
        System.out.println("play wma:"+filename);
    }
}
```

**3、定义PlayerAdapter播放器适配器类实现MusicPlayer音乐播放器接口**

引入MyPlayer

实现play方法，根据传入的文件类型进行判断，播放音乐

```java
public class PlayerAdapter implements MusicPlayer{
    private MyPlayer player;

    public PlayerAdapter(){
        this.player = new MyPlayer();
    }

    @Override
    public void play(String type, String filename) {
        if ("mp3".equals(type)){
            this.player.playMp3(filename);
        }
        if ("wma".equals(type)){
            this.player.playWma(filename);
        }
    }
}
```

**4、测试类测试**

```java
public class Test {
    public static void main(String[] args) {
        MusicPlayer player = new PlayerAdapter();
        player.play("mp3","七里香.mp3");
        player.play("wma","晴天.wma");
    }
}
```

![](https://gitee.com/janeroad/iamge-cloud/raw/master/NoteImage/image-20211010183851531.png)





### 总结

使用适配器可以让代码干净整洁易于维护，减少大量重复的判断和使用。



## 代理模式

### 简介

代理模式我们开发中经常用到，比如数据库访问层、中间件、MyBatis等等



### 举例

**1、定义房子的接口**

定义找房子的方法

```java
public interface House {
    public void findHouse();
}
```

**2、定义房子中介类实现房子接口**

```java
public class HouseProxy implements House{

    private House house;

    public HouseProxy(House house){
        this.house = house;
    }

    @Override
    public void findHouse() {
        System.out.println("日志：找了一个代理");
        this.house.findHouse();
    }
}
```

**3、定义小明类，实现房子接口**

主要是为了实现找房子的方法

```java
public class XiaoMing implements House{

    @Override
    public void findHouse() {
        System.out.println("找房子");
    }
}
```

**4、测试类测试**

```java
public class Test {
    public static void main(String[] args) {
        House house = new XiaoMing();
        HouseProxy proxy = new HouseProxy(house);
        proxy.findHouse();
    }
}
```



### 总结

实际开发中使用的代理模式会更强大的，因为它广泛运用在我们使用的中间件上。



## 模板方法模式

### 简介

模板模式核心思路在定义抽象方法的执行顺序，把抽象方法定义成只有子类能实现

### 举例

**1、定义做饭的抽象类**

```java
public abstract class Cook {
    public void open(){
        System.out.println("打开抽油烟机");
    }

    public void fire(){
        System.out.println("生火");
    }

    public abstract void doCook();

    public void outFire(){
        System.out.println("关火");
    }

    public void close(){
        System.out.println("关闭抽油烟机");
    }

    public final void cook(){
        this.open();
        this.fire();
        this.doCook();
        this.outFire();
        this.close();
    }
}
```

**2、定义CookPotato继承做饭类**

```java
public class CookPotato extends Cook{
    @Override
    public void doCook() {
        System.out.println("炒土豆丝");
    }
}
```

**3、定义CookTomato继承做饭类**

```java
public class CookTomato extends Cook{
    @Override
    public void doCook() {
        System.out.println("西红柿炒鸡蛋");
    }
}
```

**4、测试类测试**

```java
public class Test {
    public static void main(String[] args) {
        Cook cook = new CookTomato();
        cook.cook();
        cook = new CookPotato();
        System.out.println("-----------炒下一道菜-----------");
        cook.cook();
    }
}
```





### 总结

上述案例中做饭的模板都定义好了，开油烟机、生火、炒菜、关火、关油烟机，具体的做饭只要实现炒菜的方法就行了。这统一了执行的顺序，非常的规范，控制好了逻辑。





## 责任链模式

### 简介

责任链模式的核心是解决一组服务中先后执行处理的关系

### 举例

**1、定义抽象类帖子**

```java
public class Post {
    private String content;

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }
}
```

**2、定义抽象类PostHandler 帖子管理器**

handlerRequest方法用来接收帖子

next方法用来传递给下一个待处理的帖子

```java
public abstract class PostHandler {
    private PostHandler handler;

    public void setHandler(PostHandler handler){
        this.handler = handler;
    }

    public abstract void handlerRequest(Post post);

    protected final void next(Post post){
        if (Objects.nonNull(this.handler)){
            this.handler.handlerRequest(post);
        }
    }
}
```

**3、定义帖子处理器A继承PostHandler**

专门用来过滤广告的处理器

```java
public class HandlerA extends PostHandler{
    @Override
    public void handlerRequest(Post post) {
        String content = post.getContent();
        content = content.replace("广告", "**");
        post.setContent(content);
        System.out.println("过滤广告完成");
        next(post);
    }
}
```

**4、定义帖子处理器B继承PostHandler**

专门用来过滤游戏推广的处理器

~~~java
public class HandlerB extends PostHandler{
    @Override
    public void handlerRequest(Post post) {
        String content = post.getContent();
        content = content.replace("游戏推广", "**");
        post.setContent(content);
        System.out.println("过滤游戏推广完成");
        next(post);
    }
}
~~~

**5、测试类测试**

```java
public class Test {
    public static void main(String[] args) {
        PostHandler handlerA = new HandlerA();
        PostHandler handlerB = new HandlerB();
        handlerA.setHandler(handlerB);
        Post post = new Post();
        post.setContent("正常内容，广告，游戏推广");
        System.out.println("过滤前的内容:" + post.getContent());
        handlerA.handlerRequest(post);
        System.out.println("过滤后的内容:" + post.getContent());
    }
}
```

![](https://gitee.com/janeroad/iamge-cloud/raw/master/NoteImage/image-20211010190326155.png)





### 总结

责任链模式可以把代码结构变得清洗干净，解决了很多if语句的使用，当我们需要扩充链路的时候很好扩展，也方便给外部调用。
