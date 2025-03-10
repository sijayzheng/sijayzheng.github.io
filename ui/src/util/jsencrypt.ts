import JSEncrypt from 'jsencrypt'
// 密钥对生成 http://web.chacuo.net/netrsakeypair

const publicKey = import.meta.env.VITE_APP_PUBLIC_KEY


// 加密
export const encrypt = (txt: string) => {
    const encryptor = new JSEncrypt()
    encryptor.setPublicKey(publicKey) // 设置公钥
    return encryptor.encrypt(txt) // 对数据进行加密
}

