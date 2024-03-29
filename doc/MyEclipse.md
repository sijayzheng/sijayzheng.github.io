## 准备

如果安装有旧版的 MyEclipse，先卸载旧版的 MyEclipse，然后将其残留文件（用户目录下的.myeclipse.properties 文件及.myeclipse
和.webclipse 文件夹，工作空间中的.metadata 文件夹）删除

## 安装

1. 双击安装程序开始安装

![pic42.png](img/pic42.png)

2. Next，选择 I accept the terms of the license agreement

![pic28.png](img/pic28.png)

3. Next，选择安装路径为 D:\\MyEclipse

![pic21.png](img/pic21.png)

4. Next

![pic31.png](img/pic31.png)

5. 不要取消 Lauch MyEclipse CodeMix Bundles，点击 Finish，完成安装

![pic43.png](img/pic43.png)

6. 启动后选择 Next

![pic44.png](img/pic44.png)

7. 选择主题，Next

![pic45.png](img/pic45.png)

8. 选择工作空间，并启动

![pic46.png](img/pic46.png)

9. 选择 Start Trial，然后选择 Register Later

![pic48.png](img/pic48.png)

![pic47.png](img/pic47.png)

10. 等待 progress 完成后关闭 MyEclipse，进行激活

## 激活

#### 删除文件

将其用户目录下的.myeclipse.properties 文件及.myeclipse 和.webclipse 文件夹删除

#### 替换文件

将破解工具下 plugins 中破解文件复制到 D:\\MyEclipse\\plugins 文件夹中

![pic23.png](img/pic23.png)

#### 生成激活码

1. 双击打开破解工具 cracker.jar

![pic15.png](img/pic15.png)

2. 填写 Usercode，选择 BLING(图文不符，建议 BLING)，点击 SystemId（不出现再点击一次），
   点击 Tools-\>RebuildKey， 点击 Active，点击 Tools-\>SaveProperties

![pic32.png](img/pic32.png)

3. 将生成的 publicKey.bytes 替换 MyEclipse 安装目录 D:
   \\MyEclipse\\plugins\\com.genuitec.eclipse.core_16.0.0.202009011746.jar 中的 com\\genuitec\\eclipse\\core 下的
   publicKey.bytes

![pic30.png](img/pic30.png)

#### 激活 CodeMix

1. 点击 Help->CodeMix->Update License…

   ![pic49.png](img/pic49.png)

2. 点击 Enter Key

   ![pic51.png](img/pic51.png)

3. 将破解工具中的 LICENSEE 填入 Subscriber，LICENSE_KEY 填入 Subscription Code，点击 Apply

   ![pic52.png](img/pic52.png)

4. 选择 I already have an activation code，点击 Continue

   ![pic53.png](img/pic53.png)

5. 点击 Back

   ![pic54.png](img/pic54.png)

6. 找到 systemId，将其填入破解工具的 SystemId 中，点击 Tools->RebuildKey，然后点击 Active

   ![pic55.png](img/pic55.png)

7. 关闭 MyEclipse，将生成的 publicKey.bytes 替换 MyEclipse 安装目录下 D:
   \MyEclipse\plugins\com.genuitec.eclipse.code_3.6.0.202007220053 中 com\genuitec\eclipse\code 下的 publicKey.bytes

   ![pic56.png](img/pic56.png)

8. 启动 MyEclipse，点击 Help->CodeMix->Update License…

   ![pic50.png](img/pic50.png)

9. 点击 Activate Ke

   ![pic57.png](img/pic57.png)

10. 选择 I alrady have an activation code，点击 Continue

    ![pic58.png](img/pic58.png)

11. 将生成的 ACTIVATION_KEY 填入，点击 Done，即可完成激活

    ![pic59.png](img/pic59.png)

## 修改设置

#### 调整 MyEclipse 内存大小

打开 D:\\MyEclipse\\myclipse.ini，修改参数

修改 -Xms256m -Xmx4096m

添加 -XX:PermSize=256M -XX:MaxPermSize=4096M

修改完成后如图所示

![pic26.png](img/pic26.png)

#### 修改 MyEclipse 的设置

启动 MyEclipse，打开 window-\>preferences 进行 MyEclipse 的设置

1. 修改所有的文件编码为 UTF-8

![pic29.png](img/pic29.png)

找到 General-\>Content Types，选择 Text，将 Default encoding 设为 UTF-8，点击 Update

2. 打开自动保存

![pic33.png](img/pic33.png)

找到 General-\>Editors-\>Autosave，选中 Enable autosave for dirty
editors，修改一个合适的自动保存时间，点击 Apply

3. 关闭拼写检查

![pic25.png](img/pic25.png)

找到 General-\>Editors-\>Text Editors-\>Spelling，取消选中 Enable spell
checking，点击 Apply

4. 关闭不需要的启动项（根据自己的需要关闭，也可全部关闭）

![pic1.png](img/pic1.png)

5. 设置工作空间编码为 UTF-8

![pic18.png](img/pic18.png)

找到 General-\>Workspace，将 Text file encoding 修改为 UTF-8，点击 Apply

6. 修改 jsp 编码

![pic27.png](img/pic27.png)

7. 添加新的 jre

![pic17.png](img/pic17.png)

8. 修改 Maven 配置

![pic20.png](img/pic20.png)

9. 新建 Tomcat 服务器

![pic16.png](img/pic16.png)

10. 修改 git 配置，填写自己注册 git 时的邮箱和姓名

![pic2.png](img/pic2.png)

11. 取消 build 时所有的校验，Build 下的全部取消

![pic22.png](img/pic22.png)

## 问题解决

### 编辑器由于行太长，光标自动向左跑到最左边的问题

Preference -> File and Editors -> Common Editor Preferences

取消勾选 enable folding

### 样式修改

解决方案：

启动 MyEclipse，打开 window-\>preferences

找到 DevStyle，取消 Use enhanced DevStyle Themes，点击 Apply and
Close，并重新启动 MyEclipse

![pic19.png](img/pic19.png)

### 需要 5 天内激活

![pic24.png](img/pic24.png)

解决方案：

删除 C:\\User\\Administrator\\.myeclipse.properties（Administrator 为当前用户的用户名），并重新激活。

### 进入后界面缺失

解决方案：重新安装，安装按完成后先启动 MyEclipse，选择试用，然后等待 MyEclipse 中的 Progress 完成后，关闭 MyEclipse，然后删除
C:\\User\\Administrator\\.myeclipse.properties（Administrator 为当前用户的用户名），并激活。

### 卡顿

1. 关闭 MyEclipse
2. 进入 MyEclipse 的安装目录
3. 然后新建目录`disabled`，在`disabled`下新建目录`features`和`plugins`
4. 将`features`下以`org.eclipse.jpt.`开头的文件和文件夹移动到`disabled/features`下，将`plugins`下以`org.eclipse.jpt.`
   开头的文件和文件夹移动到`disabled/plugins`下

或再 MyEclipse 的安装目录使用如下命令

```bash
mkdir disabled
mkdir disabled\features
mkdir disabled\plugins

move plugins\org.eclipse.jpt.* disabled\plugins
move features\org.eclipse.jpt.* disabled\features
```
