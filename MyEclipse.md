## 准备

如果安装有旧版的 MyEclipse，先卸载旧版的 MyEclipse，然后将其残留文件（用户目录下的.myeclipse.properties 文件及.myeclipse 和.webclipse 文件夹，工作空间中的.metadata 文件夹）删除

## 安装

1. 双击安装程序开始安装

![](img/eaee01bcd11c0f6b8ddf806456ee5c09.png)

2. Next，选择 I accept the terms of the license agreement

![](img/a8c420d8ada37ebee40580d1ffa1a858.png)

3. Next，选择安装路径为 D:\\MyEclipse

![](img/3d4758eede2d0be7b5038b337eecc9f1.png)

4. Next

![](img/bfbe439d9de8a7715ed439cae08d3149.png)

5. 不要取消 Lauch MyEclipse CodeMix Bundles，点击 Finish，完成安装

![image-20201222174006560](img/image-20201222174006560.png)

6. 启动后选择 Next

![image-20201222174049640](img/image-20201222174049640.png)

7. 选择主题，Next

![image-20201222174105350](img/image-20201222174105350.png)

8. 选择工作空间，并启动

![image-20201222174124981](img/image-20201222174124981.png)

9. 选择 Start Trial，然后选择 Register Later

![image-20201222174157549](img/image-20201222174157549.png)

![image-20201222174147439](img/image-20201222174147439.png)

10. 等待 progress 完成后关闭 MyEclipse，进行激活

## 激活

#### 删除文件

将其用户目录下的.myeclipse.properties 文件及.myeclipse 和.webclipse 文件夹删除

#### 替换文件

将破解工具下 plugins 中破解文件复制到 D:\\MyEclipse\\plugins 文件夹中

![](img/49945152ac67de1f430257826ae09381.png)

#### 生成激活码

1. 双击打开破解工具 cracker.jar

![](img/1d8c9f0bb63f7d92e01d44b0d0122533.png)

2. 填写 Usercode，选择 BLING(图文不符，建议 BLING)，点击 SystemId（不出现再点击一次），
   点击 Tools-\>RebuildKey， 点击 Active，点击 Tools-\>SaveProperties

![](img/c66a27bade09e8c7d8ff64df7746a4ee.png)

3. 将生成的 publicKey.bytes 替换 MyEclipse 安装目录 D:\\MyEclipse\\plugins\\com.genuitec.eclipse.core_16.0.0.202009011746.jar 中的 com\\genuitec\\eclipse\\core 下的 publicKey.bytes

![](img/bbe742d78da886eb5a0c04e8ea31c03e.png)

#### 激活 CodeMix

1. 点击 Help->CodeMix->Update License…
   
   ![image-20210224085500005](img/image-20210224085500005.png)

2. 点击 Enter Key
   
   ![image-20210224085513340](img/image-20210224085513340.png)

3. 将破解工具中的 LICENSEE 填入 Subscriber，LICENSE_KEY 填入 Subscription Code，点击 Apply
   
   ![image-20210224085521427](img/image-20210224085521427.png)

4. 选择 I already have an activation code，点击 Continue
   
   ![image-20210224085527658](img/image-20210224085527658.png)

5. 点击 Back
   
   ![image-20210224085531451](img/image-20210224085531451.png)

6. 找到 systemId，将其填入破解工具的 SystemId 中，点击 Tools->RebuildKey，然后点击 Active
   
   ![image-20210224085537365](img/image-20210224085537365.png)

7. 关闭 MyEclipse，将生成的 publicKey.bytes 替换 MyEclipse 安装目录下 D:\MyEclipse\plugins\com.genuitec.eclipse.code_3.6.0.202007220053 中 com\genuitec\eclipse\code 下的 publicKey.bytes
   
   ![image-20210224085541538](img/image-20210224085541538.png)

8. 启动 MyEclipse，点击 Help->CodeMix->Update License…
   
   ![image-20210224085504603](img/image-20210224085504603.png)

9. 点击 Activate Ke
   
   ![image-20210224085547846](img/image-20210224085547846.png)

10. 选择 I alrady have an activation code，点击 Continue
    
    ![image-20210224085551491](img/image-20210224085551491.png)

11. 将生成的 ACTIVATION_KEY 填入，点击 Done，即可完成激活

![image-20210224085555450](img/image-20210224085555450.png)

## 修改设置

#### 调整 MyEclipse 内存大小

打开 D:\\MyEclipse\\myclipse.ini，修改参数

修改 -Xms256m -Xmx4096m

添加 -XX:PermSize=256M -XX:MaxPermSize=4096M

修改完成后如图所示

![](img/6a8eaa7bc7c97d450c79f8f7eb85a7aa.png)

#### 修改 MyEclipse 的设置

启动 MyEclipse，打开 window-\>preferences 进行 MyEclipse 的设置

1. 修改所有的文件编码为 UTF-8

![](img/b9f1868811a75b5614bf065cb6f3a884.png)

找到 General-\>Content Types，选择 Text，将 Default encoding 设为 UTF-8，点击 Update

2. 打开自动保存

![](img/c930c8fc04761f53323e10cb026417f2.png)

找到 General-\>Editors-\>Autosave，选中 Enable autosave for dirty
editors，修改一个合适的自动保存时间，点击 Apply

3. 关闭拼写检查

![](img/618561363ffbdd485a3c673687bec311.png)

找到 General-\>Editors-\>Text Editors-\>Spelling，取消选中 Enable spell
checking，点击 Apply

4. 关闭不需要的启动项（根据自己的需要关闭，也可全部关闭）

![](img/00f94483e11f9101467ffe092231e5e0.png)

5. 设置工作空间编码为 UTF-8

![](img/2d46a42b23154b330ea7a760923c0451.png)

找到 General-\>Workspace，将 Text file encoding 修改为 UTF-8，点击 Apply

6. 修改 jsp 编码

![](img/7c069784afb791291ddeb34968ff52bc.png)

7. 添加新的 jre

![](img/263191c0bbc869c1930a880a8f8ef349.png)

8. 取消 Maven 自动更新索引

![](img/d29806506972001b784cb1a83dc13a9a.png)

9. 修改 Maven 配置

![](img/338e9957f97ebf030aef47f318cfcc16.png)

10. 新建 Tomcat 服务器

![](img/23ca8dddb0c83aaac03146ce69bf3ea1.png)

11. 修改 git 配置，填写自己注册 git 时的邮箱和姓名

![](img/0793e42c2c9ab719949acb12f2e28025.png)

12. 取消 build 时所有的校验，Build 下的全部取消

![](img/3ee70cd75a1cbf9a977bcdcf62b33043.png)

## 问题解决

### myeclipse 编辑器由于行太长，光标自动向左跑到最左边的问题

Preference -> File and Editors -> Common Editor Preferences

取消勾选 enable folding

### MyEclipse 样式修改

解决方案：

启动 MyEclipse，打开 window-\>preferences

找到 DevStyle，取消 Use enhanced DevStyle Themes，点击 Apply and
Close，并重新启动 MyEclipse

![](img/2f5b9ebb4150b303b34ea9e7b89254d9.png)

### MyEclipse 需要 5 天内激活

![](img/607b161a46de22eac3898fdaace30a82.png)

解决方案：

删除 C:\\User\\Administrator\\.myeclipse.properties（Administrator 为当前用户的用户名），并重新激活。

### MyEclipse 进入后界面缺失

解决方案：重新安装，安装按完成后先启动 MyEclipse，选择试用，然后等待 MyEclipse 中的 Progress 完成后，关闭 MyEclipse，然后删除 C:\\User\\Administrator\\.myeclipse.properties（Administrator 为当前用户的用户名），并激活。
