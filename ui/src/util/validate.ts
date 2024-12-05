export const validateUtil = {
  /**
   * 判断url是否是http或https
   * @returns {Boolean}
   * @param url
   */
  isHttp: (url) => {
    return url.indexOf('http://') !== -1 || url.indexOf('https://') !== -1
  },
  /**
   * 判断path是否为外链
   * @param {string} path
   * @returns {Boolean}
   */
  isExternal: (path) => {
    return /^(https?:|mailto:|tel:)/.test(path)
  },
  /**
   * @param {string} str
   * @returns {Boolean}
   */
  validUsername: (str) => {
    const valid_map = ['admin', 'editor']
    return valid_map.indexOf(str.trim()) >= 0
  },
  /**
   * @param {string} url
   * @returns {Boolean}
   */
  validURL: (url) => {
    const reg =
      /^(https?|ftp):\/\/([a-zA-Z0-9.-]+(:[a-zA-Z0-9.&%$-]+)*@)*((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]?)(\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])){3}|([a-zA-Z0-9-]+\.)*[a-zA-Z0-9-]+\.(com|edu|gov|int|mil|net|org|biz|arpa|info|name|pro|aero|coop|museum|[a-zA-Z]{2}))(:[0-9]+)*(\/($|[a-zA-Z0-9.,?'\\+&%$#=~_-]+))*$/
    return reg.test(url)
  },
  /**
   * @param {string} str
   * @returns {Boolean}
   */
  validLowerCas: (str) => {
    const reg = /^[a-z]+$/
    return reg.test(str)
  },
  /**
   * @param {string} str
   * @returns {Boolean}
   */
  validUpperCas: (str) => {
    const reg = /^[A-Z]+$/
    return reg.test(str)
  },
  /**
   * @param {string} str
   * @returns {Boolean}
   */
  validAlphabets: (str) => {
    const reg = /^[A-Za-z]+$/
    return reg.test(str)
  },
  /**
   * @param {string} email
   * @returns {Boolean}
   */
  validEmail: (email) => {
    const reg =
      /^(([^<>()\]\\.,;:\s@"]+(\.[^<>()\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    return reg.test(email)
  },
  /**
   * @param {string} str
   * @returns {Boolean}
   */
  isString: (str) => {
    return typeof str === 'string' || str instanceof String
  },
  /**
   * @param {Array} arg
   * @returns {Boolean}
   */
  isArray: (arg) => {
    if (typeof Array.isArray === 'undefined') {
      return Object.prototype.toString.call(arg) === '[object Array]'
    }
    return Array.isArray(arg)
  }
}
