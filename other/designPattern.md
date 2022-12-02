> 设计模式的六大原则
>
> 1. 开闭原则
>    使程序扩展性好,易于维护升级,在扩展时不修改原有代码
> 2. 里氏代换原则
>    子类必定可以出现在基类出现的地方
> 3. 依赖倒转原则
>    面向接口,不依赖具体实现
> 4. 接口隔离原则
>    使用多个隔离的接口,降低耦合度
> 5. 迪米特原则（最少知道原则）
>    实体之间尽量少的发生相互作用,使功能模块相对独立
> 6. 合成复用原则
>    优先使用合成/聚合而不是继承

## 创建型模式

创建对象时隐藏创建逻辑

### 1. 工厂模式（Factory Pattern）

通过工厂类将接口实例化为不同的子类并返回抽象的接口对象

### 2. 抽象工厂模式（Abstract Factory Pattern）

工厂的工厂，通过抽象工厂获取工厂对象

### 3. 单例模式（Singleton Pattern）

对象从创建到被销毁都是唯一的

### 4. 建造者模式（Builder Pattern）

通过 builder 类返回依赖类有共同点的对象

### 5. 原型模式（Prototype Pattern）

## 结构型模式

这些设计模式关注类和对象的组合。继承的概念被用来组合接口和定义组合对象获得新功能的方式。

### 6. 适配器模式（Adapter Pattern）

### 7. 桥接模式（Bridge Pattern）

### 8. 过滤器模式（Filter、Criteria Pattern）

### 9. 组合模式（Composite Pattern）

### 10. 装饰器模式（Decorator Pattern）

### 11. 外观模式（Facade Pattern）

### 12. 享元模式（Flyweight Pattern）

### 13. 代理模式（Proxy Pattern）

## 行为型模式

这些设计模式特别关注对象之间的通信。

### 14. 责任链模式（Chain of Responsibility Pattern）

### 15. 命令模式（Command Pattern）

### 16. 解释器模式（Interpreter Pattern）

### 17. 迭代器模式（Iterator Pattern）

### 18. 中介者模式（Mediator Pattern）

### 19. 备忘录模式（Memento Pattern）

### 20. 观察者模式（Observer Pattern）

### 21. 状态模式（State Pattern）

### 22. 空对象模式（Null Object Pattern）

### 23. 策略模式（Strategy Pattern）

### 24. 模板模式（Template Pattern）

### 25. 访问者模式（Visitor Pattern）
