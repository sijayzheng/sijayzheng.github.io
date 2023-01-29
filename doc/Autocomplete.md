> 重要说明：与配置选项类似，Autocomplete 插件的方法也不是直接调用，而且通过 autocomplete()方法进行间接调用。例如：`$("#title").autocomplete( "方法名", "参数1", "参数2" );`

> Autocomplete 的方法列表

- `close()` 关闭自动完成显示的菜单。 `$( "#title" ).autocomplete( "close" );`
- `destroy()` 完全移除自动完成功能。 `$( "#title" ).autocomplete( "destroy" );`
- `disable()` 禁用 Autocomplete。 `$( "#title" ).autocomplete( "disable" );`
- `enable()` 启用 Autocomplete。 `$( "#title" ).autocomplete( "enable" );`
- `instance()` 返回 Autocomplete 的对象实例。如果指定元素没有关联的实例，则返回 undefined。 `$( "#title" ).autocomplete( "instance" );`
- `option( [ optionName [, value ] ] )` 设置或返回 Autocomplete 的配置选项。该方法有以下 4 种形式：

1. 形式一：以对象形式返回所有配置选项。 `var options = $( "#title" ).autocomplete( "option" );`
2. 形式二：根据选项名称获取单个配置选项 `var isDisabled= $( "#title" ).autocomplete( "option", "disabled" );`
3. 形式三：设置指定配置选项的值 `$( "#title" ).autocomplete( "option", "disabled", true );`
4. 形式四：以对象形式同时设置一个或多个配置选项的值 `$( "#title" ).autocomplete( "option", { "disabled": true, "delay": 500 } );`

- `search( [ value ] )` 触发 search 事件，如果该事件未被取消的话，Autocomplete 将调用数据源来显示菜单。如果没有为其指定 value 参数，它将当前输入元素的值(指定了的话，就使用指定的 value 值)。 `$( "#title" ).autocomplete( "search", "Chin" );`
- `widget()` 返回匹配菜单元素的 jQuery 对象(实际匹配一个 div 元素，该 div 内用于放置显示菜单的 html 内容)。尽管菜单项是即时创建和销毁的，但菜单元素本身并不会重复创建和销毁。它在初始化时被创建，然后一直被重复使用。 `$( "#title" ).autocomplete( "widget" );`
