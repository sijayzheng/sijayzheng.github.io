let loadingInstance
const loading = {
  start: () => {
    let progress = document.getElementById('loading-container')
    if (!progress) {
      progress = document.createElement('div')
      progress.id = 'loading-container'
      progress.innerHTML = '<div id="lc-spinner"><div class="spinner-icon"></div></div>'
      document.body.appendChild(progress)
    }
  },
  stop: () => {
    const progress = document.getElementById('loading-container')
    progress && progress.parentElement && progress.parentElement.removeChild(progress)
  },
  elLoading() {
    loadingInstance = ElLoading.service({
      lock: true,
      text: 'Loading',
      background: 'rgba(0, 0, 0, 0.7)',
    })
  },
  closeLoading() {
    loadingInstance.close()
  },
}
export default loading
