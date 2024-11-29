const NProgress = {
  version: '1.0.0',
  settings: {
    minimum: 0.08,
    easing: 'linear',
    positionUsing: '',
    speed: 200,
    trickle: true,
    trickleSpeed: 200,
    barSelector: '[role="bar"]',
    parent: 'body',
    template: '<div class="bar" role="bar"><div class="peg"></div></div>'
  },
  configure: function (options) {
    let key, value;
    for (key in options) {
      value = options[key];
      if (value !== undefined && options.hasOwnProperty(key)) {
        NProgress.settings[key] = value;
      }
    }
    return this;
  },
  start: function () {
    if (!status) {
      NProgress.set(0);
    }
    const work = function () {
      setTimeout(function () {
        if (!status) {
          return;
        }
        inc()
        work();
      }, NProgress.settings.trickleSpeed);
    }
    if (NProgress.settings.trickle) {
      work();
    }
    return this;
  },
  done: function (force) {
    if (!force && !status) {
      return this;
    }
    return inc(0.3 + 0.5 * Math.random()).set(1);
  },
  set: function (n) {
    const started = typeof status === 'number'
    n = clamp(n, NProgress.settings.minimum, 1);
    status = (n === 1 ? null : n);
    const progress = NProgress.render(!started), bar = progress.querySelector(NProgress.settings.barSelector),
      speed = NProgress.settings.speed, ease = NProgress.settings.easing;
    progress.offsetWidth; /* Repaint */
    queue(function (next) {
      // Set positionUsing if it hasn't already been set
      if (NProgress.settings.positionUsing === '') {
        const bodyStyle = document.body.style;
        const vendorPrefix = ('WebkitTransform' in bodyStyle) ? 'Webkit' : ('MozTransform' in bodyStyle) ? 'Moz' : ('msTransform' in bodyStyle) ? 'ms' : ('OTransform' in bodyStyle) ? 'O' : '';
        if (vendorPrefix + 'Perspective' in bodyStyle) {
          NProgress.settings.positionUsing = 'translate3d';
        } else if (vendorPrefix + 'Transform' in bodyStyle) {
          NProgress.settings.positionUsing = 'translate';
        } else {
          NProgress.settings.positionUsing = 'margin';
        }
      }
      let barCSS;
      if (NProgress.settings.positionUsing === 'translate3d') {
        barCSS = {transform: 'translate3d(' + toBarPerc(n) + '%,0,0)'};
      } else if (NProgress.settings.positionUsing === 'translate') {
        barCSS = {transform: 'translate(' + toBarPerc(n) + '%,0)'};
      } else {
        barCSS = {'margin-left': toBarPerc(n) + '%'};
      }
      barCSS.transition = 'all ' + speed + 'ms ' + ease;
      // Add transition
      css(bar, barCSS);
      if (n === 1) {
        // Fade out
        css(progress, {
          transition: 'none',
          opacity: 1
        });
        progress.offsetWidth; /* Repaint */
        setTimeout(function () {
          css(progress, {
            transition: 'all ' + speed + 'ms linear',
            opacity: 0
          });
          setTimeout(function () {
            removeClass(document.documentElement, 'nprogress-busy');
            const parent = isDOM(NProgress.settings.parent) ? NProgress.settings.parent : document.querySelector(NProgress.settings.parent);
            removeClass(parent, 'nprogress-custom-parent')
            const progress = document.getElementById('nprogress');
            progress && progress && progress.parentNode && progress.parentNode.removeChild(progress)
            next();
          }, speed);
        }, speed);
      } else {
        setTimeout(next, speed);
      }
    });
    return this;
  },
  render: function (fromStart) {
    if (!!document.getElementById('nprogress')) {
      return document.getElementById('nprogress');
    }
    addClass(document.documentElement, 'nprogress-busy');
    const progress = document.createElement('div');
    progress.id = 'nprogress';
    progress.innerHTML = NProgress.settings.template;
    let bar = progress.querySelector(NProgress.settings.barSelector)
    let perc = fromStart ? '-100' : toBarPerc(status || 0)
    let parent = isDOM(NProgress.settings.parent) ? NProgress.settings.parent : document.querySelector(NProgress.settings.parent)

    css(bar, {
      transition: 'all 0 linear',
      transform: 'translate3d(' + perc + '%,0,0)'
    });

    if (parent !== document.body) {
      addClass(parent, 'nprogress-custom-parent');
    }
    parent.appendChild(progress);
    return progress;
  }

}
let status = null
const inc = function (amount) {
  let n = status;
  if (!n) {
    return NProgress.start();
  } else if (n >= 0 && n <= 1) {
    if (typeof amount !== 'number') {
      if (n >= 0 && n < 0.2) {
        amount = 0.1;
      } else if (n >= 0.2 && n < 0.5) {
        amount = 0.04;
      } else if (n >= 0.5 && n < 0.8) {
        amount = 0.02;
      } else if (n >= 0.8 && n < 0.99) {
        amount = 0.005;
      } else {
        amount = 0;
      }
    }
    return NProgress.set(clamp(n + amount, 0, 0.994));
  }
}

const queue = (function () {
  const pending = [];

  function next() {
    const fn = pending.shift();
    if (fn) {
      fn(next);
    }
  }

  return function (fn) {
    pending.push(fn);
    if (pending.length === 1) {
      next();
    }
  };
})()
const css = (function () {
  const cssPrefixes = ['Webkit', 'O', 'Moz', 'ms'], cssProps = {};

  function getVendorProp(name) {
    const style = document.body.style;
    if (name in style) {
      return name;
    }
    let i = cssPrefixes.length;
    const capName = name.charAt(0).toUpperCase() + name.slice(1);
    let vendorName;
    while (i--) {
      vendorName = cssPrefixes[i] + capName;
      if (vendorName in style) {
        return vendorName;
      }
    }
    return name;
  }

  function applyCss(element, prop, value) {
    prop = prop.replace(/^-ms-/, 'ms-').replace(/-([\da-z])/gi, (match, letter) => letter.toUpperCase())
    prop = cssProps[prop] || (cssProps[prop] = getVendorProp(prop));
    element.style[prop] = value;
  }

  return function (element, properties) {
    const args = arguments;
    let prop, value;
    if (args.length === 2) {
      for (prop in properties) {
        value = properties[prop];
        if (value !== undefined && properties.hasOwnProperty(prop)) {
          applyCss(element, prop, value);
        }
      }
    } else {
      applyCss(element, args[1], args[2]);
    }
  }
})()

function isDOM(obj) {
  if (typeof HTMLElement === 'object') {
    return obj instanceof HTMLElement
  }
  return (obj && typeof obj === 'object' && obj.nodeType === 1 && typeof obj.nodeName === 'string')
}

function clamp(n, min, max) {
  return (n < min) ? min : (n > max) ? max : n
}

function toBarPerc(n) {
  return (-1 + n) * 100;
}

function hasClass(element, name) {
  const list = typeof element == 'string' ? element : classList(element);
  return list.indexOf(' ' + name + ' ') >= 0;
}

function addClass(element, name) {
  const oldList = classList(element), newList = oldList + name;
  if (hasClass(oldList, name)) {
    return;
  }
  element.className = newList.substring(1);
}

function removeClass(element, name) {
  const oldList = classList(element);
  let newList;
  if (!hasClass(element, name)) {
    return;
  }
  newList = oldList.replace(' ' + name + ' ', ' ');
  element.className = newList.substring(1, newList.length - 1);
}

function classList(element) {
  return (' ' + (element && element.className || '') + ' ').replace(/\s+/gi, ' ');
}

(function () {
  let initial = 0, current = 0;
  NProgress.promise = function ($promise) {
    if (!$promise || $promise.state() === "resolved") {
      return this;
    }
    if (current === 0) {
      NProgress.start();
    }
    initial++;
    current++;
    $promise.always(function () {
      current--;
      if (current === 0) {
        initial = 0;
        NProgress.done();
      } else {
        NProgress.set((initial - current) / initial);
      }
    });
    return this;
  };
})()
export default NProgress
