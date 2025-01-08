function E(E) {
    const D={
        PASSWORD_LEVEL_0: '不存在',
        PASSWORD_LEVEL_1: '低',
        PASSWORD_LEVEL_2: '可猜测',
        PASSWORD_LEVEL_3: '安全',
        PASSWORD_LEVEL_4: '强',
        SECONDS: '秒',
        MINUTES: '分钟',
        HOURS: '小时',
        DAYS: '天',
        MONTHS: '月',
        YEARS: '年',
        CENTURIES: '世纪',
        TRILLION: '万亿',
        BILLION: '十亿',
        MILLION: '百万',
        THOUSAND: '千',
        HUNDRED: '百',
        TRIES: '次尝试',
        FEEDBACK_NO_IMPROVE: '无需改进',
        FEEDBACK_TOO_SHORT: '密码太短，请添加更多字符',
        FEEDBACK_TOO_EASY: '密码太容易被猜测，请添加更多字符',
        FEEDBACK_STRAIGHT_ROWS: '连续排列的键很容易被猜测',
        FEEDBACK_SHORT_PATTERNS: '短的键盘模式很容易被猜测',
        FEEDBACK_REPEATS_ABC: '像“aaa”这样的重复很容易被猜测',
        FEEDBACK_REPEATS_ABCABC: '像“abcabcabc”这样的重复比“abc”稍微难猜测一些',
        FEEDBACK_SEQUENCE_EASY: '像abc或6543这样的序列很容易被猜测',
        FEEDBACK_YEARS: '近年来的日期很容易被猜测',
        FEEDBACK_DATES: '日期往往很容易被猜测',
        FEEDBACK_SIMILAR_COMMON: '这与常用密码相似',
        FEEDBACK_TOP_10: '这是一个十大常见密码',
        FEEDBACK_TOP_100: '这是一个百大常见密码',
        FEEDBACK_VERY_COMMON: '这是一个非常常见的密码',
        FEEDBACK_WORD_ONLY: '一个单词本身很容易被猜测',
        FEEDBACK_NAME_ONLY: '仅名字和姓氏很容易被猜测',
        FEEDBACK_NAME_COMMON: '常见的名字和姓氏很容易被猜测',
        PWND_LEAKED: '这个密码在泄漏密码的数据库中出现了 <span class="pwd-bad">__text__</span> 次。',
        PWND_SAFE: '这个密码是 <span class="pwd-good">安全的</span>，从未在泄漏密码的数据库中出现过。',
    }

    return D[E]
}

function parseTime(a) {
    if (a < 120) {
        return s(a, !0) + " " + '秒';
    }
    var e = 3600;
    if (a < e) {
        return minutes = a / 60, s(minutes, !0) + " " + '分钟';
    }
    var n = 86400;
    if (a < 2 * n) {
        return hours = a / e, s(hours) + " " + '小时';
    }
    var _ = 30 * n;
    if (a < _) {
        return days = a / n, s(days) + " " + '天';
    }
    var i = 365 * n;
    if (a < i) {
        return months = a / _, s(months) + " " + '月';
    }
    var t = 100 * i;
    return a < 10 * t ? (years = a / i, s(years) + " " + '年') : a < 100 * t ? (centuries = a / t, s(centuries) + " " + '世纪') : (years = a / i, s(years) + " " + '年')
}

function s(a, e) {
    for (var s = "", n = Math.pow(10, 12), _ = Math.pow(10, 9), i = Math.pow(10, 6), t = Math.pow(10, 4), A = Math.pow(10, 3); a / n >= 1;) {
        s = " " + '万亿' + " " + s, a /= n;
    }
    for (; a / _ >= 1;) {
        s = " " + '十亿' + " " + s, a /= _;
    }
    for (; a / i >= 1;) {
        s = " " + '百万' + " " + s, a /= i;
    }
    for (; a / t >= 1;) {
        s = " " + '千' + " " + s, a /= t;
    }
    for (; a / A >= 1;) {
        s = " " + '百' + " " + s, a /= A;
    }
    return decimalPoint = e ? 100 : 1, s = (a = Math.round(a * decimalPoint) / decimalPoint) + s
}

const n = document.getElementById("password-check-input"), _ = document.getElementById("password-str"),
    i = document.getElementById("pass-level"), t = document.getElementById("pass-time"),
    A = document.getElementById("pass-tries"), o = document.getElementById("check-feedback"), S = {
        0: "PASSWORD_LEVEL_0", 1: "PASSWORD_LEVEL_1", 2: "PASSWORD_LEVEL_2", 3: "PASSWORD_LEVEL_3", 4: "PASSWORD_LEVEL_4"
    };

function r() {
    ga("event", "event", {
        eventCategory: "pwd-checker", eventAction: "check"
    }), document.querySelector(".password-controls").classList.remove("password-controls-clean");
    const a = zxcvbn(n.value), r = a.score, D = n.value.length;
    _.className = "str-" + r;
    const d = parseTime(a.crack_times_seconds.online_no_throttling_10_per_second);
    t.innerText = d, A.innerText = s(a.guesses) + " " + '次尝试', i.innerText = E(S[r]), i.className = "str-" + r, o.innerHTML = "", !a.feedback.length && D > 4 && r > 3 ? o.innerHTML = "<div>" + '无需改进' + "</div>" : D < 5 ? o.innerHTML = "<div>" + '密码太短，请添加更多字符' + "</div>" : a.feedback.length ? a.feedback.forEach(((a, e) => {
        o.innerHTML += "<div>" + E(a) + "</div>"
    })) : o.innerHTML = "<div>" + '密码太容易被猜测，请添加更多字符' + "</div>"
}

n.addEventListener("input", r), document.getElementById("password-eye").addEventListener("click", (function () {
    const a = document.getElementById("main");
    a.classList.contains("show-password") ? (a.classList.remove("show-password"), n.type = "password") : (a.classList.add("show-password"), n.type = "text")
})), window.setTimeout((function () {
    O(window, document, "script", "./zxcvbn.js", "zxc", "zxc")
}), 1e3);

function d(a, E) {
    fetch(a).then((async function (a) {
        E(await a.text())
    }))
}

function O(a, E, e, s, n, _, i, t) {
    a[_] = n, a[n] = a[n] || function () {
        (a[n].q = a[n].q || []).push(arguments)
    }, a[n].l = 1 * new Date, (t = E.createElement(e)).onload = i, t.async = 1, t.src = s, E.body.appendChild(t)
}

setTimeout((function () {
    const a = document.getElementsByClassName("chkbx");
    for (let E = 0; E < a.length; E++) {
        a[E].addEventListener("change", generate);
    }
    const E = document.getElementsByClassName("toggler"),
        e = {lang: "header nav ul", faq: "#faq .question", nav: "body"};

    function s(a) {
        return e[a] ? {query: e[a]} : a.indexOf("faq-") > -1 ? {query: e.faq, child: parseInt(a[4], 10)} : void 0
    }

    for (let a = 0; a < E.length; a++) {
        const n = E[a], _ = n.getAttribute("data-toggle"), i = s(_);
        n.addEventListener("click", (function () {
            let a;
            if (a = i.child ? document.querySelectorAll(i.query)[i.child - 1] : document.querySelector(i.query), "nav" === _) {
                const a = document.querySelector(e.lang);
                a && a.classList.remove("open")
            }
            const E = a.classList.contains("open");
            a.classList[E ? "remove" : "add"]("open"), ga("event", "event", {
                eventCategory: _, eventAction: E ? "close" : "open"
            })
        }))
    }
    O(window, document, "script", "https://www.googletagmanager.com/gtag/js?id=G-5RM0ZFCVP5", "ga", "GoogleAnalyticsObject", (function () {
        function a() {
            dataLayer.push(arguments)
        }

        a("js", new Date), a("config", "G-5RM0ZFCVP5")
    }))
}), 200);



