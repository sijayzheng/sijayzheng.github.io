# Vue学习

## Vue生命周期

<img src="https://vuejs.bootcss.com/images/lifecycle.png" alt="Vue 实例生命周期" style="zoom: 50%;" />

<img src="https://img-blog.csdnimg.cn/img_convert/3aac48106dfd000f3aec4349289cfbbf.png" alt="3aac48106dfd000f3aec4349289cfbbf.png" style="zoom:50%;" />

## Vue data声明方式

```js
//方式一
let vue1 = new Vue({
    id:'#app',
    data:{

    }
});

//方式二
let vue2 = new Vue({
    id:'#app',
    data(){

    }
});
```

使用方式二可以防止变量污染，不影响其他

## Vue事件修饰符

- `.stop` 阻止事件冒泡，防止触发父级事件
- `.prevent` 阻止默认事件
- `.capture` 从最外层开始提升事件优先级
- `.self` 只有点击其自身才可以有事件响应
- `.once` 只响应一次事件，无论是直接或者是间接
- `.passive` 

## Vue实例

```vue

```

## Vue从零搭建项目
