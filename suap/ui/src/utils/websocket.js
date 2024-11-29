let socketUrl = ''; // socket地址
let websocket = null; // websocket 实例
let heartTime = null; // 心跳定时器实例
let socketHeart = 0; // 心跳次数
const HeartTimeOut = 60 * 1000; // 心跳超时时间 10000 = 10s
let socketError = 0; // 错误次数

export const ws = {
  // 初始化socket
  init(url) {
    if (import.meta.env.VITE_APP_WEBSOCKET === 'false') {
      return;
    }
    socketUrl = url;
    // 初始化 websocket
    websocket = new WebSocket(url + '?Authorization=Bearer ' + getToken());
    this.onOpen();
    this.onMessage();
    this.onError();
    this.onClose();
    this.sendSocketHeart();
    return websocket;
  },
  // socket 连接成功
  onOpen() {
    websocket.onopen = () => {
      console.log('连接 websocket 成功');
      this.resetHeart()
    };
  },
  // socket 连接失败
  onError() {
    websocket.onerror = (e) => {
      console.log('连接 websocket 失败', e);
    };
  },
  // socket 断开链接
  onClose() {
    websocket.onclose = (e) => {
      console.log('断开连接', e);
    };
  },
  // socket 重置心跳
  resetHeart() {
    socketHeart = 0;
    socketError = 0;
    clearInterval(heartTime);
    this.sendSocketHeart();
  },
  // socket心跳发送
  sendSocketHeart() {
    heartTime = setInterval(() => {
      // 如果连接正常则发送心跳
      if (websocket.readyState === 1) {
        // if (socketHeart <= 30) {
        websocket.send(JSON.stringify({type: 'ping'}));
        socketHeart = socketHeart + 1;
      } else {
        // 重连
        this.reconnect();
      }
    }, HeartTimeOut);
  },
  // socket重连
  reconnect() {
    if (socketError <= 2) {
      clearInterval(heartTime);
      this.init(socketUrl);
      socketError = socketError + 1;
      // eslint-disable-next-line prettier/prettier
      console.log('socket重连', socketError);
    } else {
      // eslint-disable-next-line prettier/prettier
      console.log('重试次数已用完');
      clearInterval(heartTime);
    }
  },
  // socket 发送数据
  sendMsg(data) {
    websocket.send(data);
  },
  // socket 接收数据
  onMessage() {
    websocket.onmessage = (e) => {
      if (e.data.indexOf('heartbeat') > 0) {
        this.resetHeart();
      }
      if (e.data.indexOf('ping') > 0) {
        return;
      }
      // useNoticeStore().addNotice({
      //   message: e.data,
      //   read: false,
      //   time: new Date().toLocaleString()
      // });
      ElNotification({
        title: '消息',
        message: e.data,
        type: 'success',
        duration: 3000
      });
      return e.data;
    };
  }
}

