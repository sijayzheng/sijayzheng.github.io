<template>
  <el-scrollbar>
    <div class="card-container">
      <el-divider>
        System
      </el-divider>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Plus')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-plus></i-plus>
          </el-icon>
          <el-text tag="p">
            Plus
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Minus')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-minus></i-minus>
          </el-icon>
          <el-text tag="p">
            Minus
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('CirclePlus')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-circleplus></i-circleplus>
          </el-icon>
          <el-text tag="p">
            CirclePlus
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Search')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-search></i-search>
          </el-icon>
          <el-text tag="p">
            Search
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Female')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-female></i-female>
          </el-icon>
          <el-text tag="p">
            Female
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Male')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-male></i-male>
          </el-icon>
          <el-text tag="p">
            Male
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Aim')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-aim></i-aim>
          </el-icon>
          <el-text tag="p">
            Aim
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('House')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-house></i-house>
          </el-icon>
          <el-text tag="p">
            House
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('FullScreen')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-fullscreen></i-fullscreen>
          </el-icon>
          <el-text tag="p">
            FullScreen
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Loading')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-loading></i-loading>
          </el-icon>
          <el-text tag="p">
            Loading
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Link')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-link></i-link>
          </el-icon>
          <el-text tag="p">
            Link
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Service')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-service></i-service>
          </el-icon>
          <el-text tag="p">
            Service
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Pointer')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-pointer></i-pointer>
          </el-icon>
          <el-text tag="p">
            Pointer
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Star')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-star></i-star>
          </el-icon>
          <el-text tag="p">
            Star
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Notification')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-notification></i-notification>
          </el-icon>
          <el-text tag="p">
            Notification
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Connection')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-connection></i-connection>
          </el-icon>
          <el-text tag="p">
            Connection
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ChatDotRound')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-chatdotround></i-chatdotround>
          </el-icon>
          <el-text tag="p">
            ChatDotRound
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Setting')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-setting></i-setting>
          </el-icon>
          <el-text tag="p">
            Setting
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Clock')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-clock></i-clock>
          </el-icon>
          <el-text tag="p">
            Clock
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Position')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-position></i-position>
          </el-icon>
          <el-text tag="p">
            Position
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Discount')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-discount></i-discount>
          </el-icon>
          <el-text tag="p">
            Discount
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Odometer')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-odometer></i-odometer>
          </el-icon>
          <el-text tag="p">
            Odometer
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ChatSquare')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-chatsquare></i-chatsquare>
          </el-icon>
          <el-text tag="p">
            ChatSquare
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ChatRound')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-chatround></i-chatround>
          </el-icon>
          <el-text tag="p">
            ChatRound
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ChatLineRound')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-chatlineround></i-chatlineround>
          </el-icon>
          <el-text tag="p">
            ChatLineRound
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ChatLineSquare')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-chatlinesquare></i-chatlinesquare>
          </el-icon>
          <el-text tag="p">
            ChatLineSquare
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ChatDotSquare')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-chatdotsquare></i-chatdotsquare>
          </el-icon>
          <el-text tag="p">
            ChatDotSquare
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('View')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-view></i-view>
          </el-icon>
          <el-text tag="p">
            View
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Hide')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-hide></i-hide>
          </el-icon>
          <el-text tag="p">
            Hide
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Unlock')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-unlock></i-unlock>
          </el-icon>
          <el-text tag="p">
            Unlock
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Lock')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-lock></i-lock>
          </el-icon>
          <el-text tag="p">
            Lock
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('RefreshRight')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-refreshright></i-refreshright>
          </el-icon>
          <el-text tag="p">
            RefreshRight
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('RefreshLeft')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-refreshleft></i-refreshleft>
          </el-icon>
          <el-text tag="p">
            RefreshLeft
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Refresh')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-refresh></i-refresh>
          </el-icon>
          <el-text tag="p">
            Refresh
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Bell')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-bell></i-bell>
          </el-icon>
          <el-text tag="p">
            Bell
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('MuteNotification')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-mutenotification></i-mutenotification>
          </el-icon>
          <el-text tag="p">
            MuteNotification
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('User')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-user></i-user>
          </el-icon>
          <el-text tag="p">
            User
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Check')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-check></i-check>
          </el-icon>
          <el-text tag="p">
            Check
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('CircleCheck')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-circlecheck></i-circlecheck>
          </el-icon>
          <el-text tag="p">
            CircleCheck
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Warning')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-warning></i-warning>
          </el-icon>
          <el-text tag="p">
            Warning
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('CircleClose')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-circleclose></i-circleclose>
          </el-icon>
          <el-text tag="p">
            CircleClose
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Close')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-close></i-close>
          </el-icon>
          <el-text tag="p">
            Close
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('PieChart')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-piechart></i-piechart>
          </el-icon>
          <el-text tag="p">
            PieChart
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('More')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-more></i-more>
          </el-icon>
          <el-text tag="p">
            More
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Compass')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-compass></i-compass>
          </el-icon>
          <el-text tag="p">
            Compass
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Filter')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-filter></i-filter>
          </el-icon>
          <el-text tag="p">
            Filter
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Switch')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-switch></i-switch>
          </el-icon>
          <el-text tag="p">
            Switch
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Select')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-select></i-select>
          </el-icon>
          <el-text tag="p">
            Select
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('SemiSelect')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-semiselect></i-semiselect>
          </el-icon>
          <el-text tag="p">
            SemiSelect
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('CloseBold')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-closebold></i-closebold>
          </el-icon>
          <el-text tag="p">
            CloseBold
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('EditPen')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-editpen></i-editpen>
          </el-icon>
          <el-text tag="p">
            EditPen
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Edit')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-edit></i-edit>
          </el-icon>
          <el-text tag="p">
            Edit
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Message')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-message></i-message>
          </el-icon>
          <el-text tag="p">
            Message
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('MessageBox')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-messagebox></i-messagebox>
          </el-icon>
          <el-text tag="p">
            MessageBox
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('TurnOff')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-turnoff></i-turnoff>
          </el-icon>
          <el-text tag="p">
            TurnOff
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Finished')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-finished></i-finished>
          </el-icon>
          <el-text tag="p">
            Finished
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Delete')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-delete></i-delete>
          </el-icon>
          <el-text tag="p">
            Delete
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Crop')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-crop></i-crop>
          </el-icon>
          <el-text tag="p">
            Crop
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('SwitchButton')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-switchbutton></i-switchbutton>
          </el-icon>
          <el-text tag="p">
            SwitchButton
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Operation')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-operation></i-operation>
          </el-icon>
          <el-text tag="p">
            Operation
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Open')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-open></i-open>
          </el-icon>
          <el-text tag="p">
            Open
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Remove')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-remove></i-remove>
          </el-icon>
          <el-text tag="p">
            Remove
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ZoomOut')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-zoomout></i-zoomout>
          </el-icon>
          <el-text tag="p">
            ZoomOut
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ZoomIn')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-zoomin></i-zoomin>
          </el-icon>
          <el-text tag="p">
            ZoomIn
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('InfoFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-infofilled></i-infofilled>
          </el-icon>
          <el-text tag="p">
            InfoFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('CircleCheckFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-circlecheckfilled></i-circlecheckfilled>
          </el-icon>
          <el-text tag="p">
            CircleCheckFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('SuccessFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-successfilled></i-successfilled>
          </el-icon>
          <el-text tag="p">
            SuccessFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('WarningFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-warningfilled></i-warningfilled>
          </el-icon>
          <el-text tag="p">
            WarningFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('CircleCloseFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-circleclosefilled></i-circleclosefilled>
          </el-icon>
          <el-text tag="p">
            CircleCloseFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('QuestionFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-questionfilled></i-questionfilled>
          </el-icon>
          <el-text tag="p">
            QuestionFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('WarnTriangleFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-warntrianglefilled></i-warntrianglefilled>
          </el-icon>
          <el-text tag="p">
            WarnTriangleFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('UserFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-userfilled></i-userfilled>
          </el-icon>
          <el-text tag="p">
            UserFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('MoreFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-morefilled></i-morefilled>
          </el-icon>
          <el-text tag="p">
            MoreFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Tools')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-tools></i-tools>
          </el-icon>
          <el-text tag="p">
            Tools
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('HomeFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-homefilled></i-homefilled>
          </el-icon>
          <el-text tag="p">
            HomeFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Menu')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-menu></i-menu>
          </el-icon>
          <el-text tag="p">
            Menu
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('UploadFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-uploadfilled></i-uploadfilled>
          </el-icon>
          <el-text tag="p">
            UploadFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Avatar')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-avatar></i-avatar>
          </el-icon>
          <el-text tag="p">
            Avatar
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('HelpFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-helpfilled></i-helpfilled>
          </el-icon>
          <el-text tag="p">
            HelpFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Share')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-share></i-share>
          </el-icon>
          <el-text tag="p">
            Share
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('StarFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-starfilled></i-starfilled>
          </el-icon>
          <el-text tag="p">
            StarFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Comment')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-comment></i-comment>
          </el-icon>
          <el-text tag="p">
            Comment
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Histogram')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-histogram></i-histogram>
          </el-icon>
          <el-text tag="p">
            Histogram
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Grid')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-grid></i-grid>
          </el-icon>
          <el-text tag="p">
            Grid
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Promotion')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-promotion></i-promotion>
          </el-icon>
          <el-text tag="p">
            Promotion
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('DeleteFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-deletefilled></i-deletefilled>
          </el-icon>
          <el-text tag="p">
            DeleteFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('RemoveFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-removefilled></i-removefilled>
          </el-icon>
          <el-text tag="p">
            RemoveFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('CirclePlusFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-circleplusfilled></i-circleplusfilled>
          </el-icon>
          <el-text tag="p">
            CirclePlusFilled
          </el-text>
        </div>
      </el-card>
      <el-divider> Arrow</el-divider>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ArrowLeft')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-arrowleft></i-arrowleft>
          </el-icon>
          <el-text tag="p">
            ArrowLeft
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ArrowUp')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-arrowup></i-arrowup>
          </el-icon>
          <el-text tag="p">
            ArrowUp
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ArrowRight')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-arrowright></i-arrowright>
          </el-icon>
          <el-text tag="p">
            ArrowRight
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ArrowDown')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-arrowdown></i-arrowdown>
          </el-icon>
          <el-text tag="p">
            ArrowDown
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ArrowLeftBold')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-arrowleftbold></i-arrowleftbold>
          </el-icon>
          <el-text tag="p">
            ArrowLeftBold
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ArrowUpBold')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-arrowupbold></i-arrowupbold>
          </el-icon>
          <el-text tag="p">
            ArrowUpBold
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ArrowRightBold')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-arrowrightbold></i-arrowrightbold>
          </el-icon>
          <el-text tag="p">
            ArrowRightBold
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ArrowDownBold')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-arrowdownbold></i-arrowdownbold>
          </el-icon>
          <el-text tag="p">
            ArrowDownBold
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('DArrowRight')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-darrowright></i-darrowright>
          </el-icon>
          <el-text tag="p">
            DArrowRight
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('DArrowLeft')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-darrowleft></i-darrowleft>
          </el-icon>
          <el-text tag="p">
            DArrowLeft
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Download')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-download></i-download>
          </el-icon>
          <el-text tag="p">
            Download
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Upload')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-upload></i-upload>
          </el-icon>
          <el-text tag="p">
            Upload
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Top')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-top></i-top>
          </el-icon>
          <el-text tag="p">
            Top
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Bottom')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-bottom></i-bottom>
          </el-icon>
          <el-text tag="p">
            Bottom
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Back')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-back></i-back>
          </el-icon>
          <el-text tag="p">
            Back
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Right')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-right></i-right>
          </el-icon>
          <el-text tag="p">
            Right
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('TopRight')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-topright></i-topright>
          </el-icon>
          <el-text tag="p">
            TopRight
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('TopLeft')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-topleft></i-topleft>
          </el-icon>
          <el-text tag="p">
            TopLeft
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('BottomRight')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-bottomright></i-bottomright>
          </el-icon>
          <el-text tag="p">
            BottomRight
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('BottomLeft')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-bottomleft></i-bottomleft>
          </el-icon>
          <el-text tag="p">
            BottomLeft
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Sort')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-sort></i-sort>
          </el-icon>
          <el-text tag="p">
            Sort
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('SortUp')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-sortup></i-sortup>
          </el-icon>
          <el-text tag="p">
            SortUp
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('SortDown')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-sortdown></i-sortdown>
          </el-icon>
          <el-text tag="p">
            SortDown
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Rank')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-rank></i-rank>
          </el-icon>
          <el-text tag="p">
            Rank
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('CaretLeft')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-caretleft></i-caretleft>
          </el-icon>
          <el-text tag="p">
            CaretLeft
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('CaretTop')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-carettop></i-carettop>
          </el-icon>
          <el-text tag="p">
            CaretTop
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('CaretRight')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-caretright></i-caretright>
          </el-icon>
          <el-text tag="p">
            CaretRight
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('CaretBottom')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-caretbottom></i-caretbottom>
          </el-icon>
          <el-text tag="p">
            CaretBottom
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('DCaret')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-dcaret></i-dcaret>
          </el-icon>
          <el-text tag="p">
            DCaret
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Expand')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-expand></i-expand>
          </el-icon>
          <el-text tag="p">
            Expand
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Fold')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-fold></i-fold>
          </el-icon>
          <el-text tag="p">
            Fold
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Document')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-document></i-document>
          </el-icon>
          <el-text tag="p">
            Document
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('DocumentAdd')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-documentadd></i-documentadd>
          </el-icon>
          <el-text tag="p">
            DocumentAdd
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Document')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-document></i-document>
          </el-icon>
          <el-text tag="p">
            Document
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Notebook')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-notebook></i-notebook>
          </el-icon>
          <el-text tag="p">
            Notebook
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Tickets')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-tickets></i-tickets>
          </el-icon>
          <el-text tag="p">
            Tickets
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Memo')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-memo></i-memo>
          </el-icon>
          <el-text tag="p">
            Memo
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Collection')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-collection></i-collection>
          </el-icon>
          <el-text tag="p">
            Collection
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Postcard')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-postcard></i-postcard>
          </el-icon>
          <el-text tag="p">
            Postcard
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ScaleToOriginal')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-scaletooriginal></i-scaletooriginal>
          </el-icon>
          <el-text tag="p">
            ScaleToOriginal
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('SetUp')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-setup></i-setup>
          </el-icon>
          <el-text tag="p">
            SetUp
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('DocumentDelete')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-documentdelete></i-documentdelete>
          </el-icon>
          <el-text tag="p">
            DocumentDelete
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('DocumentChecked')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-documentchecked></i-documentchecked>
          </el-icon>
          <el-text tag="p">
            DocumentChecked
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('DataBoard')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-databoard></i-databoard>
          </el-icon>
          <el-text tag="p">
            DataBoard
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('DataAnalysis')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-dataanalysis></i-dataanalysis>
          </el-icon>
          <el-text tag="p">
            DataAnalysis
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('CopyDocument')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-copydocument></i-copydocument>
          </el-icon>
          <el-text tag="p">
            CopyDocument
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('FolderChecked')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-folderchecked></i-folderchecked>
          </el-icon>
          <el-text tag="p">
            FolderChecked
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Files')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-files></i-files>
          </el-icon>
          <el-text tag="p">
            Files
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Folder')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-folder></i-folder>
          </el-icon>
          <el-text tag="p">
            Folder
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('FolderDelete')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-folderdelete></i-folderdelete>
          </el-icon>
          <el-text tag="p">
            FolderDelete
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('FolderRemove')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-folderremove></i-folderremove>
          </el-icon>
          <el-text tag="p">
            FolderRemove
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('FolderOpened')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-folderopened></i-folderopened>
          </el-icon>
          <el-text tag="p">
            FolderOpened
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('DocumentCopy')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-documentcopy></i-documentcopy>
          </el-icon>
          <el-text tag="p">
            DocumentCopy
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('DocumentRemove')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-documentremove></i-documentremove>
          </el-icon>
          <el-text tag="p">
            DocumentRemove
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('FolderAdd')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-folderadd></i-folderadd>
          </el-icon>
          <el-text tag="p">
            FolderAdd
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('FirstAidKit')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-firstaidkit></i-firstaidkit>
          </el-icon>
          <el-text tag="p">
            FirstAidKit
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Reading')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-reading></i-reading>
          </el-icon>
          <el-text tag="p">
            Reading
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('DataLine')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-dataline></i-dataline>
          </el-icon>
          <el-text tag="p">
            DataLine
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Management')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-management></i-management>
          </el-icon>
          <el-text tag="p">
            Management
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Checked')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-checked></i-checked>
          </el-icon>
          <el-text tag="p">
            Checked
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Ticket')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-ticket></i-ticket>
          </el-icon>
          <el-text tag="p">
            Ticket
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Failed')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-failed></i-failed>
          </el-icon>
          <el-text tag="p">
            Failed
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('TrendCharts')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-trendcharts></i-trendcharts>
          </el-icon>
          <el-text tag="p">
            TrendCharts
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('List')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-list></i-list>
          </el-icon>
          <el-text tag="p">
            List
          </el-text>
        </div>
      </el-card>
      <el-divider>Media</el-divider>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Microphone')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-microphone></i-microphone>
          </el-icon>
          <el-text tag="p">
            Microphone
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Mute')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-mute></i-mute>
          </el-icon>
          <el-text tag="p">
            Mute
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Mic')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-mic></i-mic>
          </el-icon>
          <el-text tag="p">
            Mic
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('VideoPause')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-videopause></i-videopause>
          </el-icon>
          <el-text tag="p">
            VideoPause
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('VideoCamera')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-videocamera></i-videocamera>
          </el-icon>
          <el-text tag="p">
            VideoCamera
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('VideoPlay')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-videoplay></i-videoplay>
          </el-icon>
          <el-text tag="p">
            VideoPlay
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Headset')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-headset></i-headset>
          </el-icon>
          <el-text tag="p">
            Headset
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Monitor')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-monitor></i-monitor>
          </el-icon>
          <el-text tag="p">
            Monitor
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Film')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-film></i-film>
          </el-icon>
          <el-text tag="p">
            Film
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Camera')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-camera></i-camera>
          </el-icon>
          <el-text tag="p">
            Camera
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Picture')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-picture></i-picture>
          </el-icon>
          <el-text tag="p">
            Picture
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('PictureRounded')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-picturerounded></i-picturerounded>
          </el-icon>
          <el-text tag="p">
            PictureRounded
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Iphone')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-iphone></i-iphone>
          </el-icon>
          <el-text tag="p">
            Iphone
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Cellphone')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-cellphone></i-cellphone>
          </el-icon>
          <el-text tag="p">
            Cellphone
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('VideoCameraFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-videocamerafilled></i-videocamerafilled>
          </el-icon>
          <el-text tag="p">
            VideoCameraFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('PictureFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-picturefilled></i-picturefilled>
          </el-icon>
          <el-text tag="p">
            PictureFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Platform')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-platform></i-platform>
          </el-icon>
          <el-text tag="p">
            Platform
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('CameraFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-camerafilled></i-camerafilled>
          </el-icon>
          <el-text tag="p">
            CameraFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('BellFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-bellfilled></i-bellfilled>
          </el-icon>
          <el-text tag="p">
            BellFilled
          </el-text>
        </div>
      </el-card>
      <el-divider> Traffic</el-divider>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Location')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-location></i-location>
          </el-icon>
          <el-text tag="p">
            Location
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('LocationInformation')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-locationinformation></i-locationinformation>
          </el-icon>
          <el-text tag="p">
            LocationInformation
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('DeleteLocation')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-deletelocation></i-deletelocation>
          </el-icon>
          <el-text tag="p">
            DeleteLocation
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Coordinate')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-coordinate></i-coordinate>
          </el-icon>
          <el-text tag="p">
            Coordinate
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Bicycle')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-bicycle></i-bicycle>
          </el-icon>
          <el-text tag="p">
            Bicycle
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('OfficeBuilding')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-officebuilding></i-officebuilding>
          </el-icon>
          <el-text tag="p">
            OfficeBuilding
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('School')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-school></i-school>
          </el-icon>
          <el-text tag="p">
            School
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Guide')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-guide></i-guide>
          </el-icon>
          <el-text tag="p">
            Guide
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('AddLocation')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-addlocation></i-addlocation>
          </el-icon>
          <el-text tag="p">
            AddLocation
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('MapLocation')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-maplocation></i-maplocation>
          </el-icon>
          <el-text tag="p">
            MapLocation
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Place')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-place></i-place>
          </el-icon>
          <el-text tag="p">
            Place
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('LocationFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-locationfilled></i-locationfilled>
          </el-icon>
          <el-text tag="p">
            LocationFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Van')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-van></i-van>
          </el-icon>
          <el-text tag="p">
            Van
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Food')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-food></i-food>
          </el-icon>
          <el-text tag="p">
            Food
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Watermelon')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-watermelon></i-watermelon>
          </el-icon>
          <el-text tag="p">
            Watermelon
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Pear')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-pear></i-pear>
          </el-icon>
          <el-text tag="p">
            Pear
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('NoSmoking')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-nosmoking></i-nosmoking>
          </el-icon>
          <el-text tag="p">
            NoSmoking
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Smoking')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-smoking></i-smoking>
          </el-icon>
          <el-text tag="p">
            Smoking
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Mug')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-mug></i-mug>
          </el-icon>
          <el-text tag="p">
            Mug
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('GobletSquareFull')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-gobletsquarefull></i-gobletsquarefull>
          </el-icon>
          <el-text tag="p">
            GobletSquareFull
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('GobletFull')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-gobletfull></i-gobletfull>
          </el-icon>
          <el-text tag="p">
            GobletFull
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('KnifeFork')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-knifefork></i-knifefork>
          </el-icon>
          <el-text tag="p">
            KnifeFork
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Sugar')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-sugar></i-sugar>
          </el-icon>
          <el-text tag="p">
            Sugar
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Bowl')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-bowl></i-bowl>
          </el-icon>
          <el-text tag="p">
            Bowl
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('MilkTea')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-milktea></i-milktea>
          </el-icon>
          <el-text tag="p">
            MilkTea
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Lollipop')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-lollipop></i-lollipop>
          </el-icon>
          <el-text tag="p">
            Lollipop
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Coffee')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-coffee></i-coffee>
          </el-icon>
          <el-text tag="p">
            Coffee
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Chicken')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-chicken></i-chicken>
          </el-icon>
          <el-text tag="p">
            Chicken
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Dish')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-dish></i-dish>
          </el-icon>
          <el-text tag="p">
            Dish
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('IceTea')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-icetea></i-icetea>
          </el-icon>
          <el-text tag="p">
            IceTea
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ColdDrink')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-colddrink></i-colddrink>
          </el-icon>
          <el-text tag="p">
            ColdDrink
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('CoffeeCup')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-coffeecup></i-coffeecup>
          </el-icon>
          <el-text tag="p">
            CoffeeCup
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('DishDot')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-dishdot></i-dishdot>
          </el-icon>
          <el-text tag="p">
            DishDot
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('IceDrink')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-icedrink></i-icedrink>
          </el-icon>
          <el-text tag="p">
            IceDrink
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('IceCream')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-icecream></i-icecream>
          </el-icon>
          <el-text tag="p">
            IceCream
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Dessert')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-dessert></i-dessert>
          </el-icon>
          <el-text tag="p">
            Dessert
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('IceCreamSquare')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-icecreamsquare></i-icecreamsquare>
          </el-icon>
          <el-text tag="p">
            IceCreamSquare
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ForkSpoon')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-forkspoon></i-forkspoon>
          </el-icon>
          <el-text tag="p">
            ForkSpoon
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('IceCreamRound')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-icecreamround></i-icecreamround>
          </el-icon>
          <el-text tag="p">
            IceCreamRound
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Food')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-food></i-food>
          </el-icon>
          <el-text tag="p">
            Food
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('HotWater')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-hotwater></i-hotwater>
          </el-icon>
          <el-text tag="p">
            HotWater
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Grape')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-grape></i-grape>
          </el-icon>
          <el-text tag="p">
            Grape
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Fries')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-fries></i-fries>
          </el-icon>
          <el-text tag="p">
            Fries
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Apple')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-apple></i-apple>
          </el-icon>
          <el-text tag="p">
            Apple
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Burger')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-burger></i-burger>
          </el-icon>
          <el-text tag="p">
            Burger
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Goblet')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-goblet></i-goblet>
          </el-icon>
          <el-text tag="p">
            Goblet
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('GobletSquare')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-gobletsquare></i-gobletsquare>
          </el-icon>
          <el-text tag="p">
            GobletSquare
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Orange')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-orange></i-orange>
          </el-icon>
          <el-text tag="p">
            Orange
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Cherry')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-cherry></i-cherry>
          </el-icon>
          <el-text tag="p">
            Cherry
          </el-text>
        </div>
      </el-card>
      <el-divider>Items</el-divider>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Printer')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-printer></i-printer>
          </el-icon>
          <el-text tag="p">
            Printer
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Calendar')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-calendar></i-calendar>
          </el-icon>
          <el-text tag="p">
            Calendar
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('CreditCard')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-creditcard></i-creditcard>
          </el-icon>
          <el-text tag="p">
            CreditCard
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Box')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-box></i-box>
          </el-icon>
          <el-text tag="p">
            Box
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Money')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-money></i-money>
          </el-icon>
          <el-text tag="p">
            Money
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Refrigerator')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-refrigerator></i-refrigerator>
          </el-icon>
          <el-text tag="p">
            Refrigerator
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Cpu')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-cpu></i-cpu>
          </el-icon>
          <el-text tag="p">
            Cpu
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Football')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-football></i-football>
          </el-icon>
          <el-text tag="p">
            Football
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Brush')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-brush></i-brush>
          </el-icon>
          <el-text tag="p">
            Brush
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Suitcase')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-suitcase></i-suitcase>
          </el-icon>
          <el-text tag="p">
            Suitcase
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('SuitcaseLine')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-suitcaseline></i-suitcaseline>
          </el-icon>
          <el-text tag="p">
            SuitcaseLine
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Umbrella')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-umbrella></i-umbrella>
          </el-icon>
          <el-text tag="p">
            Umbrella
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('AlarmClock')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-alarmclock></i-alarmclock>
          </el-icon>
          <el-text tag="p">
            AlarmClock
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Medal')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-medal></i-medal>
          </el-icon>
          <el-text tag="p">
            Medal
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('GoldMedal')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-goldmedal></i-goldmedal>
          </el-icon>
          <el-text tag="p">
            GoldMedal
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Present')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-present></i-present>
          </el-icon>
          <el-text tag="p">
            Present
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Mouse')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-mouse></i-mouse>
          </el-icon>
          <el-text tag="p">
            Mouse
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Watch')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-watch></i-watch>
          </el-icon>
          <el-text tag="p">
            Watch
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('QuartzWatch')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-quartzwatch></i-quartzwatch>
          </el-icon>
          <el-text tag="p">
            QuartzWatch
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Magnet')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-magnet></i-magnet>
          </el-icon>
          <el-text tag="p">
            Magnet
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Help')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-help></i-help>
          </el-icon>
          <el-text tag="p">
            Help
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Soccer')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-soccer></i-soccer>
          </el-icon>
          <el-text tag="p">
            Soccer
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ToiletPaper')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-toiletpaper></i-toiletpaper>
          </el-icon>
          <el-text tag="p">
            ToiletPaper
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ReadingLamp')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-readinglamp></i-readinglamp>
          </el-icon>
          <el-text tag="p">
            ReadingLamp
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Paperclip')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-paperclip></i-paperclip>
          </el-icon>
          <el-text tag="p">
            Paperclip
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('MagicStick')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-magicstick></i-magicstick>
          </el-icon>
          <el-text tag="p">
            MagicStick
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Basketball')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-basketball></i-basketball>
          </el-icon>
          <el-text tag="p">
            Basketball
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Baseball')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-baseball></i-baseball>
          </el-icon>
          <el-text tag="p">
            Baseball
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Coin')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-coin></i-coin>
          </el-icon>
          <el-text tag="p">
            Coin
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Goods')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-goods></i-goods>
          </el-icon>
          <el-text tag="p">
            Goods
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Sell')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-sell></i-sell>
          </el-icon>
          <el-text tag="p">
            Sell
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('SoldOut')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-soldout></i-soldout>
          </el-icon>
          <el-text tag="p">
            SoldOut
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Key')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-key></i-key>
          </el-icon>
          <el-text tag="p">
            Key
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ShoppingCart')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-shoppingcart></i-shoppingcart>
          </el-icon>
          <el-text tag="p">
            ShoppingCart
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ShoppingCartFull')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-shoppingcartfull></i-shoppingcartfull>
          </el-icon>
          <el-text tag="p">
            ShoppingCartFull
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ShoppingTrolley')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-shoppingtrolley></i-shoppingtrolley>
          </el-icon>
          <el-text tag="p">
            ShoppingTrolley
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Phone')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-phone></i-phone>
          </el-icon>
          <el-text tag="p">
            Phone
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Scissor')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-scissor></i-scissor>
          </el-icon>
          <el-text tag="p">
            Scissor
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Handbag')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-handbag></i-handbag>
          </el-icon>
          <el-text tag="p">
            Handbag
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ShoppingBag')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-shoppingbag></i-shoppingbag>
          </el-icon>
          <el-text tag="p">
            ShoppingBag
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Trophy')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-trophy></i-trophy>
          </el-icon>
          <el-text tag="p">
            Trophy
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('TrophyBase')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-trophybase></i-trophybase>
          </el-icon>
          <el-text tag="p">
            TrophyBase
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Stopwatch')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-stopwatch></i-stopwatch>
          </el-icon>
          <el-text tag="p">
            Stopwatch
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Timer')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-timer></i-timer>
          </el-icon>
          <el-text tag="p">
            Timer
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('CollectionTag')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-collectiontag></i-collectiontag>
          </el-icon>
          <el-text tag="p">
            CollectionTag
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('TakeawayBox')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-takeawaybox></i-takeawaybox>
          </el-icon>
          <el-text tag="p">
            TakeawayBox
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('PriceTag')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-pricetag></i-pricetag>
          </el-icon>
          <el-text tag="p">
            PriceTag
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Wallet')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-wallet></i-wallet>
          </el-icon>
          <el-text tag="p">
            Wallet
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Opportunity')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-opportunity></i-opportunity>
          </el-icon>
          <el-text tag="p">
            Opportunity
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('PhoneFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-phonefilled></i-phonefilled>
          </el-icon>
          <el-text tag="p">
            PhoneFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('WalletFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-walletfilled></i-walletfilled>
          </el-icon>
          <el-text tag="p">
            WalletFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('GoodsFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-goodsfilled></i-goodsfilled>
          </el-icon>
          <el-text tag="p">
            GoodsFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Flag')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-flag></i-flag>
          </el-icon>
          <el-text tag="p">
            Flag
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('BrushFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-brushfilled></i-brushfilled>
          </el-icon>
          <el-text tag="p">
            BrushFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Briefcase')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-briefcase></i-briefcase>
          </el-icon>
          <el-text tag="p">
            Briefcase
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Stamp')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-stamp></i-stamp>
          </el-icon>
          <el-text tag="p">
            Stamp
          </el-text>
        </div>
      </el-card>
      <el-divider>Weather</el-divider>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Sunrise')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-sunrise></i-sunrise>
          </el-icon>
          <el-text tag="p">
            Sunrise
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Sunny')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-sunny></i-sunny>
          </el-icon>
          <el-text tag="p">
            Sunny
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Ship')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-ship></i-ship>
          </el-icon>
          <el-text tag="p">
            Ship
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('MostlyCloudy')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-mostlycloudy></i-mostlycloudy>
          </el-icon>
          <el-text tag="p">
            MostlyCloudy
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('PartlyCloudy')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-partlycloudy></i-partlycloudy>
          </el-icon>
          <el-text tag="p">
            PartlyCloudy
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Sunset')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-sunset></i-sunset>
          </el-icon>
          <el-text tag="p">
            Sunset
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Drizzling')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-drizzling></i-drizzling>
          </el-icon>
          <el-text tag="p">
            Drizzling
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Pouring')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-pouring></i-pouring>
          </el-icon>
          <el-text tag="p">
            Pouring
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Cloudy')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-cloudy></i-cloudy>
          </el-icon>
          <el-text tag="p">
            Cloudy
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Moon')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-moon></i-moon>
          </el-icon>
          <el-text tag="p">
            Moon
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('MoonNight')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-moonnight></i-moonnight>
          </el-icon>
          <el-text tag="p">
            MoonNight
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Lightning')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-lightning></i-lightning>
          </el-icon>
          <el-text tag="p">
            Lightning
          </el-text>
        </div>
      </el-card>
      <el-divider>Other</el-divider>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ChromeFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-chromefilled></i-chromefilled>
          </el-icon>
          <el-text tag="p">
            ChromeFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Eleme')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-eleme></i-eleme>
          </el-icon>
          <el-text tag="p">
            Eleme
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ElemeFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-elemefilled></i-elemefilled>
          </el-icon>
          <el-text tag="p">
            ElemeFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('ElementPlus')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-elementplus></i-elementplus>
          </el-icon>
          <el-text tag="p">
            ElementPlus
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('Shop')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-shop></i-shop>
          </el-icon>
          <el-text tag="p">
            Shop
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('SwitchFilled')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-switchfilled></i-switchfilled>
          </el-icon>
          <el-text tag="p">
            SwitchFilled
          </el-text>
        </div>
      </el-card>
      <el-card :body-style="bodyStyle" shadow="hover" class="card" @click="copy('WindPower')">
        <div class="container">
          <el-icon :size="iconSize">
            <i-windpower></i-windpower>
          </el-icon>
          <el-text tag="p">
            WindPower
          </el-text>
        </div>
      </el-card>
    </div>
  </el-scrollbar>
</template>

<script setup name="icons">
import {camelTobreak} from '@/utils'

const iconSize = ref(20)
const bodyStyle = ref('padding:4px;height:100%')

const copy = (icon) => {
  icon = 'i-' + camelTobreak(icon)
  navigator.clipboard.writeText(`<el-icon><${icon}></${icon}></el-icon>`)
}

</script>

<style scoped lang="scss">
.card-container {
  display: flex;
  flex-wrap: wrap;

  .card {
    height: 100px;
    width: 120px;
    cursor: pointer;
    margin: 4px;

    .container {
      height: 100%;
      width: 100%;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
    }
  }
}

</style>
