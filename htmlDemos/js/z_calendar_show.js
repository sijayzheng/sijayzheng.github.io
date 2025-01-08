var SukuDofs = 12;
var KyuuseiName3 = new Array('门中太乙明，星官号贪狼，赌彩财喜旺，婚姻大吉昌。<br>出入无阻挡，参谒见贤良，此行三五里，黑衣别阴阳。',
'门前见摄提，百事必忧疑，相生犹自可，相克祸必临。<br>死门并相会，老妇哭悲啼，求谋并吉事，尽皆不相宜。<br>只可藏隐遁，若动伤身疾。',
'出入会轩辕，凡事必缠牵，相生全不美，相克更忧煎。<br>远行多不利，博彩尽输钱，九天玄女法，句句不虚言。',
'招摇号木星，当之事莫行，相克行人阻，阴人口舌迎。<br>梦寐多惊惧，屋响斧自鸣，阴阳消息理，万法弗违情。',
'五鬼为天符，当门阴女谋，相克无好事，行路阻中途。<br>走失难寻觅，道逢有尼姑，此星当门值，万事有灾除。',
'神光跃青龙，财气喜重重，投入有酒食，赌彩最兴隆。<br>更逢相生旺，休言克破凶，见贵安营寨，万事总吉同。',
'吾将为咸池，当之尽不宜，出入多不利，相克有灾情。<br>赌彩全输尽，求财空手回，仙人真妙语，愚人莫与知，<br>动用虚惊退，反复逆风吹。',
'坐临太阴星，百祸不相侵，求谋悉成就，知交有觅寻。<br>回风归来路，恐有殃伏起，密语中记取，慎乎莫轻行。',
'迎来天乙星，相逢百事兴，运用和合庆，茶酒喜相迎。<br>求谋并嫁娶，好合有天成。祸福如神验，吉凶甚分明。');
var Sukuyou3 = new Array('角星造作主荣昌，外进田财及女郎，嫁娶婚姻出贵子，文人及第见君王，惟有埋葬不可用，叁年之後主瘟疫，起工修建坟基地，当前立见主人凶。<br><br>释义：<br>角星造作则可荣昌，可置田产及早办喜事。如果用角星来办嫁娶则可出贵子，读书人的功名可一帆风顺。但是不可用角星行埋葬，否则叁年之後有瘟疫。如果用角星起工修建坟墓或地基，则主人不利。',
'亢星造作长房当，十日之中主有殃，田地消磨官失职，接运定是虎狼伤，嫁娶婚姻用此日，儿孙新妇守空房，埋葬若还用此日，当时害祸主重伤。<br><br>释义：<br>亢星造作则长房在十日之中有灾殃，祖田不保且会失去官职，及会受小人所伤。如果用亢星行嫁娶，则儿孙新妇要守空房。如果用亢星行埋葬则有灾祸、重伤。',
'氐星造作主灾凶，费尽田园仓库空，埋葬不可用此日，悬绳吊颈祸重重，若是婚姻离别散，夜招浪子入房中，行船必定遭沉没，更生聋哑子孙穷。<br><br>释义：<br>氐星造作会有灾殃，田园财产一时空。用氐星进行埋葬，则有人会悬吊自缢、灾祸接二连叁。用氐星行婚礼则会离别、妇人不贞，航行又不利，更会生产聋哑的子孙，而闹得家庭更穷。',
'房星造作田园进，钱财牛马遍山岗，更招外处田庄宅，荣华富贵福禄康，埋葬若然用此日，高官进职拜君王，嫁娶嫦娥至月殿，叁年抱子至朝堂。<br><br>释义：<br>房星造作则财源滚滚来，享受荣华富贵、有福禄，并且身体健康。用房星行埋葬则仕途平稳，嫁娶用此，日婚姻美满，叁年得贵子。',
'心星造作大为凶，更遭刑讼狱囚中，忤逆官非宅产退，埋葬卒暴死相从，婚姻若是用此日，子死儿亡泪满胸，叁年之内连遭祸，事事教君没始终。<br><br>释义：<br>心星造作则大凶，有囚狱之灾，忤逆长辈、惹官非，损失宅产。埋葬则大凶，用於婚姻则伤子，凶事接二连叁，令人寝食难安。','尾星造作主天恩，富贵荣华福禄增，招财进宝兴家宅，和合婚姻贵子孙，埋葬若能依此日，男清女正子孙兴，开门放水招田宅，代代公侯远播名。<br><br>释义：<br>尾星造作可荣华富贵、福禄、财源滚滚而来。行婚姻则大吉利、子孙有利，有贵气。用尾星行埋葬则子孙兴旺，地理方面的开门放水则财源滚滚而来，并且有名望。',
'箕星造作主高强，岁岁年年大吉昌，埋葬修坟大吉利，田蚕牛马遍山岗，开门放水招田宅，箧满金银谷满仓，福荫高官加禄位，六亲丰荣乐安康。<br><br>释义：<br>箕星造作可年年大吉昌，埋葬修坟也大吉利，风水方面的开门放水，则可升官，使财源滚滚而来，六亲丰足，生活过得快乐平安且身体健康，仕途平稳。',
'斗星造作主招财，文武官员位鼎台，田宅家财千万进，坟堂修辑贵富来，开门放水招牛马，旺蚕男女主和谐，遇此吉宿来照护，时支福庆永无灾。<br><br>释义：<br>斗星造作可招财、有利於仕途，家业欣欣向荣。修辑坟地可招富贵，开门放水则有进财，可使家庭和睦，有福而无灾。','牛星造作主灾危，九横叁灾不可推，家宅不安人口退，田蚕不利主人衰，嫁娶婚姻皆自损，金银财谷渐无之，若是开门并放水，牛猪羊马亦伤悲。<br><br>释义：<br>牛星造作有灾厄，天灾横祸不能免，家庭不安而且伤人口，事业不利。如果行婚姻则不利，钱财渐退，假若开门放水则不利六畜。',
'女星造作损婆娘，兄弟相嫌似虎狼，埋葬生灾逢鬼怪，颠邪疾病主瘟惶，为事遭官财失散，泻利留连不可当，开门放水用此日，全家财散主离乡。<br><br>释义：<br>女星造作则不利妇女，恐会损人口。兄弟互相猜忌，感情不和睦，好比水火不能相容。如果行埋葬则容易招鬼怪，有怪病发生，作事易惹事生非而失财。要是开门放水，则家庭破散，离乡别井。','虚星造作主灾殃，男女孤眠不一双，内乱风声无礼节，儿孙媳妇伴人床，开门放水遭灾祸，虎咬蛇伤又卒亡，叁叁五五连年病，家破人亡不可当。<br><br>释义：<br>虚星造作则有灾殃，男女相克无法成双。家庭不和睦，而且儿孙媳妇都不守节操，甚至乱了伦理。假如开门放水更有灾祸，损人口，有伤亡，疾病接二连叁，直至家破人亡。',
'危星不可造高楼，自遭刑吊见血光，叁年孩子遭水厄，後生出外永不还，埋葬若还逢此日，周年百日取高堂，叁年两载一悲伤，开门放水到官堂。<br><br>释义：<br>危星造作则有刑吊及血光之灾，叁年内孩子会遭水厄，损人口，年青出外不归家。若行埋葬则更悲伤，周年或百日年长的有灾厄，要是开门放水，则会上官堂 (打官司) 。','室星修造进田牛，儿孙代代近王侯，家贵荣华天上至，寿如彭祖八千秋，开门放水招财帛，和合婚姻生贵儿，埋葬若能依此日，门庭兴旺福无休。<br><br>释义：<br>室星修造则大吉利，富贵荣华，而且财源广进，长寿。开门放水则可招财进宝，行婚礼则可生贵子。要是行埋葬则子孙兴旺、福禄无穷。','璧星造作主增财，丝帛大熟福滔天，奴婢自来人口进，开门放水出英贤，埋葬招财官品进，家中诸事乐陶然，婚姻吉利主贵子，早播名誉着祖鞭。<br><br>释义：<br>璧星造作可招进财、财源广进，事业有成。开打放水则後代贤能，埋葬则可招财，并且有利於仕途，家庭生活幸福美满。如果行婚礼则大吉利，早生贵子而有名声。','奎星造作得祯祥，家内荣和大吉昌，若是埋葬阴卒死，当年定主两叁伤，看看军令刑伤到，重重官事主瘟惶，开门放水遭灾祸，叁年两次损儿郎。<br><br>释义：<br>奎星造作算得了祯祥，可使家内繁荣而和睦。但是不可用来埋葬，否则一年内必有伤亡，而且有官事及怪病发生。要是开门放水则有灾祸，对儿子不利。','娄星修造起门庭，财旺家和事事兴，外进钱财百日进，一家兄弟播高名，婚姻进益生贵子，玉帛金银箱满盈，放水开门皆吉利，男荣女贵寿康宁。<br><br>释义：<br>娄星造作则可使家业兴旺，财源广进，兄弟和睦有名望。行婚礼则早生贵子，要是开门放水则身体健康而长寿，经济很好。','胃星造作事如何，家贵荣华喜气多，埋葬贵临官禄位，夫妇齐眉永保康，婚姻遇此家富贵，叁灾九祸不逢他，从此门前多吉庆，儿孙代代拜金阶。<br><br>释义：<br>胃星造作则荣华富贵，喜气洋洋。行埋葬则有利於仕途，夫妇可白首偕老。行婚礼则可使家内富贵，儿孙代代有名望。','昴星造作进田牛，埋葬官灾不得休，重丧二日叁人死，尽卖田园不记增，开门放水招灾祸，叁岁孩儿白了头，婚姻不可逢此日，死别生离是可愁。<br><br>释义：<br>昴星造作可使家业兴盛。但埋葬则常有官灾，且会继续死人，更会变卖田产。开门放水则会招灾祸，孩童会得怪病，行婚礼则更悲哀，会有死别生离。',
'毕星造作主光前，买得田园有馀钱，埋葬此日添官职，田粮大熟永丰年，开门放水多吉庆，合家人口得安然，婚姻若得逢此日，生得孩儿福寿全。<br><br>释义：<br>毕星造作则财源广进，行埋葬则有利於仕途，事业兴旺，开门放水，则合家欢乐，行婚礼则早生贵子而福寿双全。','觜星造作有徒刑，叁年必定主伶丁，埋葬卒死多因此，取定寅年使杀人，叁丧不止皆由此，一人药毒二人身，家门田地皆退败，仓库金银化作尘。<br><br>释义：<br>觜星造作则有刑害，会变成伶仃。假若行埋葬则容易有暴死的现象，多数应於寅年，灾祸不断，直至使田地退散而破家。','参星造作旺人家，文星照耀大光华，只因造作田财旺，埋葬招疾哭黄沙，开门放水加官职，房房子孙见田加，婚姻许遁遭刑克，男女朝开幕落花。<br><br>释义：<br>参星造作可旺人家，文星高照，并对田产有利，但是行埋葬则大凶。开门放水则有利於仕途，与田产及子孙兴旺。但是行婚礼则大凶，会遭刑克，感情无法和睦。','井星造作旺粮田，金榜题名第一光，埋葬须防惊卒死，狂颠风疾入黄泉，开门放水招财帛，牛马猪羊旺莫言，贵人田塘来入宅，儿孙兴旺有馀钱。<br><br>释义：<br>井星造作则财源广进，可金榜题名。埋葬则不利，容易得怪病而命归黄泉。开门放水可招财进宝，贵人重重，儿孙兴旺。','鬼星起造卒人亡，堂前不见主人郎，埋葬此日官禄至，儿孙代代近君王，开门放水须伤死，嫁娶夫妻不久长，修土建墙伤产女，手扶双女泪汪汪。<br><br>释义：<br>鬼星造作则大凶，有伤人口。埋葬用此日则可加冠进禄，对儿孙的仕途有利，但是开门放水则有伤人口，要是行婚礼夫修土建墙也大凶。',
'柳星造作主遭官，昼夜偷闭不暂安，埋葬瘟惶多疾病，田园退尽守冬寒，开门放水遭聋瞎，腰佗背曲似弓弯，更有捧刑宜谨慎，妇人随客走盘桓，<br><br>释义：<br>柳星造作则有官事，无日安宁，埋葬则多疾病，并且田产退败。开门放水则会产生耳聋及瞎眼的毛病，甚至弯背，严重的话甚至遭刑打，妇人不守妇道。','星宿日好造新房，进职加官近帝王，不可埋葬并放水，凶星临位女人亡，生离死别无心恋，要自归休别嫁郎，孔子九曲殊难度，放水开门天命伤。<br><br>释义：<br>星星造作则有利於仕途。但不可埋葬与放水，否则大凶，会遭生离死别之祸。','张星日好造龙轩，年年并见进庄田，埋葬不久升官职，代代为官近帝前，开门放水招财帛，婚姻和合福绵绵，田粮人满仓库满，百般顺意自安然。<br><br>释义：<br>张星造作则财源广进，埋葬则有利於仕途。开门放水则可招财进宝，行婚礼则夫妻恩爱和合福绵绵，事事如意，安然自得。','翼星不利架高堂，叁年二载见瘟惶，埋葬若还逢此日，子孙必定走他乡，婚姻此日不宜利，归家定是不相当，开门放水家须破，少女恋花贪外郎。<br><br>释义：<br>翼星修造则容易得怪病，要是行埋葬则子孙远走他乡。行婚礼则不利，妇女不守妇道。开门放水则家破，少女会淫奔。','轸星临水造龙宫，代代为官受皇封，富贵荣华增受禄，库满仓盈自昌隆，埋葬文昌来照助，宅舍安宁不见凶，更有为官沾帝宠，婚姻龙子入龙宫。<br><br>释义：<br>轸星造作则有利於仕途，荣华富贵，增福寿，财源广进。行埋葬则宅舍安宁。');
var zrxName1 = new Array('青龙<font color=#FF8C1A>(黄道日)</font>','明堂<font color=#FF8C1A>(黄道日)</font>','天刑<FONT color=#0000A0>(黑道日)</font>','朱雀<FONT color=#0000A0>(黑道日)</font>','金匮<font color=#FF8C1A>(黄道日)</font>','天德<font color=#FF8C1A>(黄道日)</font>','白虎<FONT color=#0000A0>(黑道日)</font>','玉堂<font color=#FF8C1A>(黄道日)</font>','天牢<FONT color=#0000A0>(黑道日)</font>','玄武<FONT color=#0000A0>(黑道日)</font>','司命<font color=#FF8C1A>(黄道日)</font>','勾陈<FONT color=#0000A0>(黑道日)</font>');
var zrxName2 = new Array('司命<font color=#FF8C1A>(黄道日)</font>','勾陈<FONT color=#0000A0>(黑道日)</font>','青龙<font color=#FF8C1A>(黄道日)</font>','明堂<font color=#FF8C1A>(黄道日)</font>','天刑<FONT color=#0000A0>(黑道日)</font>','朱雀<FONT color=#0000A0>(黑道日)</font>','金匮<font color=#FF8C1A>(黄道日)</font>','天德<font color=#FF8C1A>(黄道日)</font>','白虎<FONT color=#0000A0>(黑道日)</font>','玉堂<font color=#FF8C1A>(黄道日)</font>','天牢<FONT color=#0000A0>(黑道日)</font>','玄武<FONT color=#0000A0>(黑道日)</font>');
var zrxName3 = new Array('天牢<FONT color=#0000A0>(黑道日)</font>','玄武<FONT color=#0000A0>(黑道日)</font>','司命<font color=#FF8C1A>(黄道日)</font>','勾陈<FONT color=#0000A0>(黑道日)</font>','青龙<font color=#FF8C1A>(黄道日)</font>','明堂<font color=#FF8C1A>(黄道日)</font>','天刑<FONT color=#0000A0>(黑道日)</font>','朱雀<FONT color=#0000A0>(黑道日)</font>','金匮<font color=#FF8C1A>(黄道日)</font>','天德<font color=#FF8C1A>(黄道日)</font>','白虎<FONT color=#0000A0>(黑道日)</font>','玉堂<font color=#FF8C1A>(黄道日)</font>');
var zrxName4 = new Array('白虎<FONT color=#0000A0>(黑道日)</font>','玉堂<font color=#FF8C1A>(黄道日)</font>','天牢<FONT color=#0000A0>(黑道日)</font>','玄武<FONT color=#0000A0>(黑道日)</font>','司命<font color=#FF8C1A>(黄道日)</font>','勾陈<FONT color=#0000A0>(黑道日)</font>','青龙<font color=#FF8C1A>(黄道日)</font>','明堂<font color=#FF8C1A>(黄道日)</font>','天刑<FONT color=#0000A0>(黑道日)</font>','朱雀<FONT color=#0000A0>(黑道日)</font>','金匮<font color=#FF8C1A>(黄道日)</font>','天德<font color=#FF8C1A>(黄道日)</font>');
var zrxName5 = new Array('金匮<font color=#FF8C1A>(黄道日)</font>','天德<font color=#FF8C1A>(黄道日)</font>','白虎<FONT color=#0000A0>(黑道日)</font>','玉堂<font color=#FF8C1A>(黄道日)</font>','天牢<FONT color=#0000A0>(黑道日)</font>','玄武<FONT color=#0000A0>(黑道日)</font>','司命<font color=#FF8C1A>(黄道日)</font>','勾陈<FONT color=#0000A0>(黑道日)</font>','青龙<font color=#FF8C1A>(黄道日)</font>','明堂<font color=#FF8C1A>(黄道日)</font>','天刑<FONT color=#0000A0>(黑道日)</font>','朱雀<FONT color=#0000A0>(黑道日)</font>');
var zrxName6 = new Array('天刑<FONT color=#0000A0>(黑道日)</font>','朱雀<FONT color=#0000A0>(黑道日)</font>','金匮<font color=#FF8C1A>(黄道日)</font>','天德<font color=#FF8C1A>(黄道日)</font>','白虎<FONT color=#0000A0>(黑道日)</font>','玉堂<font color=#FF8C1A>(黄道日)</font>','天牢<FONT color=#0000A0>(黑道日)</font>','玄武<FONT color=#0000A0>(黑道日)</font>','司命<font color=#FF8C1A>(黄道日)</font>','勾陈<FONT color=#0000A0>(黑道日)</font>','青龙<font color=#FF8C1A>(黄道日)</font>','明堂<font color=#FF8C1A>(黄道日)</font>');
var zrxName7 = new Array('青龙<font color=#FF8C1A>(黄道日)</font>','明堂<font color=#FF8C1A>(黄道日)</font>','天刑<FONT color=#0000A0>(黑道日)</font>','朱雀<FONT color=#0000A0>(黑道日)</font>','金匮<font color=#FF8C1A>(黄道日)</font>','天德<font color=#FF8C1A>(黄道日)</font>','白虎<FONT color=#0000A0>(黑道日)</font>','玉堂<font color=#FF8C1A>(黄道日)</font>','天牢<FONT color=#0000A0>(黑道日)</font>','玄武<FONT color=#0000A0>(黑道日)</font>','司命<font color=#FF8C1A>(黄道日)</font>','勾陈<FONT color=#0000A0>(黑道日)</font>');
var zrxName8 = new Array('司命<font color=#FF8C1A>(黄道日)</font>','勾陈<FONT color=#0000A0>(黑道日)</font>','青龙<font color=#FF8C1A>(黄道日)</font>','明堂<font color=#FF8C1A>(黄道日)</font>','天刑<FONT color=#0000A0>(黑道日)</font>','朱雀<FONT color=#0000A0>(黑道日)</font>','金匮<font color=#FF8C1A>(黄道日)</font>','天德<font color=#FF8C1A>(黄道日)</font>','白虎<FONT color=#0000A0>(黑道日)</font>','玉堂<font color=#FF8C1A>(黄道日)</font>','天牢<FONT color=#0000A0>(黑道日)</font>','玄武<FONT color=#0000A0>(黑道日)</font>');
var zrxName9 = new Array('天牢<FONT color=#0000A0>(黑道日)</font>','玄武<FONT color=#0000A0>(黑道日)</font>','司命<font color=#FF8C1A>(黄道日)</font>','勾陈<FONT color=#0000A0>(黑道日)</font>','青龙<font color=#FF8C1A>(黄道日)</font>','明堂<font color=#FF8C1A>(黄道日)</font>','天刑<FONT color=#0000A0>(黑道日)</font>','朱雀<FONT color=#0000A0>(黑道日)</font>','金匮<font color=#FF8C1A>(黄道日)</font>','天德<font color=#FF8C1A>(黄道日)</font>','白虎<FONT color=#0000A0>(黑道日)</font>','玉堂<font color=#FF8C1A>(黄道日)</font>');
var zrxName10 = new Array('白虎<FONT color=#0000A0>(黑道日)</font>','玉堂<font color=#FF8C1A>(黄道日)</font>','天牢<FONT color=#0000A0>(黑道日)</font>','玄武<FONT color=#0000A0>(黑道日)</font>','司命<font color=#FF8C1A>(黄道日)</font>','勾陈<FONT color=#0000A0>(黑道日)</font>','青龙<font color=#FF8C1A>(黄道日)</font>','明堂<font color=#FF8C1A>(黄道日)</font>','天刑<FONT color=#0000A0>(黑道日)</font>','朱雀<FONT color=#0000A0>(黑道日)</font>','金匮<font color=#FF8C1A>(黄道日)</font>','天德<font color=#FF8C1A>(黄道日)</font>');
var zrxName11 = new Array('金匮<font color=#FF8C1A>(黄道日)</font>','天德<font color=#FF8C1A>(黄道日)</font>','白虎<FONT color=#0000A0>(黑道日)</font>','玉堂<font color=#FF8C1A>(黄道日)</font>','天牢<FONT color=#0000A0>(黑道日)</font>','玄武<FONT color=#0000A0>(黑道日)</font>','司命<font color=#FF8C1A>(黄道日)</font>','勾陈<FONT color=#0000A0>(黑道日)</font>','青龙<font color=#FF8C1A>(黄道日)</font>','明堂<font color=#FF8C1A>(黄道日)</font>','天刑<FONT color=#0000A0>(黑道日)</font>','朱雀<FONT color=#0000A0>(黑道日)</font>');
var zrxName12 = new Array('天刑<FONT color=#0000A0>(黑道日)</font>','朱雀<FONT color=#0000A0>(黑道日)</font>','金匮<font color=#FF8C1A>(黄道日)</font>','天德<font color=#FF8C1A>(黄道日)</font>','白虎<FONT color=#0000A0>(黑道日)</font>','玉堂<font color=#FF8C1A>(黄道日)</font>','天牢<FONT color=#0000A0>(黑道日)</font>','玄武<FONT color=#0000A0>(黑道日)</font>','司命<font color=#FF8C1A>(黄道日)</font>','勾陈<FONT color=#0000A0>(黑道日)</font>','青龙<font color=#FF8C1A>(黄道日)</font>','明堂<font color=#FF8C1A>(黄道日)</font>');

function jcr(d){
	var jcrjx;
	if(d=='建') jcrjx='<FONT color=#FF8C1A>宜：</font>出行,上任,会友,上书,见工<br><FONT color=#0000A0>忌：</font>动土,开仓,嫁娶,纳采';
	if(d=='除') jcrjx='<FONT color=#FF8C1A>宜：</font>除服,疗病,出行,拆卸,入宅<br><FONT color=#0000A0>忌：</font>求官,上任,开张,搬家,探病';
	if(d=='满') jcrjx='<FONT color=#FF8C1A>宜：</font>祈福,祭祀,结亲,开市,交易<br><FONT color=#0000A0>忌：</font>服药,求医,栽种,动土,迁移';
	if(d=='平') jcrjx='<FONT color=#FF8C1A>宜：</font>祭祀,修墳,涂泥,餘事勿取<br><FONT color=#0000A0>忌：</font>移徙.入宅.嫁娶.开市.安葬';
	if(d=='定') jcrjx='<FONT color=#FF8C1A>宜：</font>交易,立券,会友,签約,納畜<br><FONT color=#0000A0>忌：</font>种植,置业,卖田,掘井,造船';
	if(d=='执') jcrjx='<FONT color=#FF8C1A>宜：</font>祈福,祭祀,求子,结婚,立约<br><FONT color=#0000A0>忌：</font>开市,交易,搬家,远行';
	if(d=='破') jcrjx='<FONT color=#FF8C1A>宜：</font>求医,赴考,祭祀,餘事勿取<br><FONT color=#0000A0>忌：</font>动土,出行,移徙,开市,修造';
	if(d=='危') jcrjx='<FONT color=#FF8C1A>宜：</font>经营,交易,求官,納畜,動土<br><FONT color=#0000A0>忌：</font>登高,行船.安床.入宅.博彩';
	if(d=='成') jcrjx='<FONT color=#FF8C1A>宜：</font>祈福,入学,开市,求医,成服<br><FONT color=#0000A0>忌：</font>词讼,安門,移徙';
	if(d=='收') jcrjx='<FONT color=#FF8C1A>宜：</font>祭祀,求财,签约,嫁娶,订盟<br><FONT color=#0000A0>忌：</font>开市.安床.安葬.入宅.破土';
	if(d=='开') jcrjx='<FONT color=#FF8C1A>宜：</font>疗病,结婚,交易,入仓,求职<br><FONT color=#0000A0>忌：</font>安葬,动土,针灸';
	if(d=='闭') jcrjx='<FONT color=#FF8C1A>宜：</font>祭祀,交易,收财,安葬<br><FONT color=#0000A0>忌：</font>宴会,安床,出行,嫁娶,移徙';
	return(jcrjx);
}

function cyclical7(num,num2) {
	if (num==2)
		return(zrxName1[num2]);
	if (num==3)
		return(zrxName2[num2]);
	if (num==4)
		return(zrxName3[num2]);
	if (num==5)
		return(zrxName4[num2]);
	if (num==6)
		return(zrxName5[num2]);
	if (num==7)
		return(zrxName6[num2]);
	if (num==8)
		return(zrxName7[num2]);
	if (num==9)
		return(zrxName8[num2]);
	if (num==10)
		return(zrxName9[num2]);
	if (num==11)
		return(zrxName10[num2]);
	if (num==0)
		return(zrxName11[num2]);
	if (num==1)
		return(zrxName12[num2]);
}

function CalConv2(yy,mm,dd,y,d,m,dt,nm,nd) {
	var dy=d+''+dd
	if ((yy==0 && dd==6)||(yy==6 && dd==0)||(yy==1 && dd==7)||(yy==7 && dd==1)||(yy==2 && dd==8)||(yy==8 && dd==2)||(yy==3 && dd==9)||(yy==9 && dd==3)||(yy==4 && dd==10)||(yy==10 && dd==4)||(yy==5 && dd==11)||(yy==11 && dd==5)) {return '<FONT color=#0000A0>日值岁破 大事不宜</font>';}
	else if ((mm==0 && dd==6)||(mm==6 && dd==0)||(mm==1 && dd==7)||(mm==7 && dd==1)||(mm==2 && dd==8)||(mm==8 && dd==2)||(mm==3 && dd==9)||(mm==9 && dd==3)||(mm==4 && dd==10)||(mm==10 && dd==4)||(mm==5 && dd==11)||(mm==11 && dd==5)) {return '<FONT color=#0000A0><center>日值月破 大事不宜</font>';}
	else if ((y==0 && dy=='911')||(y==1 && dy=='55')||(y==2 && dy=='111')||(y==3 && dy=='75')||(y==4 && dy=='311')||(y==5 && dy=='95')||(y==6 && dy=='511')||(y==7 && dy=='15')||(y==8 && dy=='711')||(y==9 && dy=='35')) {return '<FONT color=#0000A0><center>日值上朔 大事不宜</font>';}
	else if ((m==1 && dt==13)||(m==2 && dt==11)||(m==3 && dt==9)||(m==4 && dt==7)||(m==5 && dt==5)||(m==6 && dt==3)||(m==7 && dt==1)||(m==7 && dt==29)||(m==8 && dt==27)||(m==9 && dt==25)||(m==10 && dt==23)||(m==11 && dt==21)||(m==12 && dt==19)) {return '<FONT color=#0000A0><center>日值杨公十三忌 大事不宜</font>';}
	else{return 0;}
}
function Year2Kyuusei(yy)
{
	return (9001 - yy) % 9;
}
function Year2KyuuseiNameS(yy) {
	var ans;
	ans = Year2Kyuusei(yy);
	return KyuuseiName[ans];
}
function Year2KyuuseiNameL(yy) {
	var ans;
	ans = Year2Kyuusei(yy);
	return KyuuseiName[ans] + KyuuseiName2[ans];
}
function Jd2KyuuseiNameS(JD)
{
	var ans;
	ans = Jd2Kyuusei(JD);
	if (ans >= 0) return KyuuseiName[ans];
	return '';
}
function Jd2KyuuseiNameL(JD)
{
	var ans;
	ans = Jd2Kyuusei(JD);
	if (ans >= 0) {
		return '九星：'+(KyuuseiName[ans] + KyuuseiName2[ans]);
	}
	return '';
}
function Jd2Kyuusei(JD)
{
	var flag,base;
	JD = Math.floor(JD);
	if ((JD < NKyuusei[0]) || (JD >= NKyuusei[0] + NKyuusei[1])){
		if (GetTenton(JD) < 0) return -1;
	}

	if (NKyuusei[2] < 0) {flag = -1;}
	else { flag = 1;}
	base = flag * NKyuusei[2] - 1 + 270;
	base += (JD - NKyuusei[0]) * flag;
	return base % 9;
}
function Jd2KyuuseiNameL2(JD)
{
	var ans;
	ans = Jd2Kyuusei(JD);
	if (ans >= 0) {
		return (KyuuseiName3[ans]);
	}
	return '';
}
function GetTenton(JD) {
	var KJD,KJDF;
	var n,ne;
	ne = KyuuseiJD.length;
	JD = Math.floor(JD);
	if (JD < KyuuseiJD[0]) return -1;
	if (JD >= KyuuseiJD[ne - 1]) return -1;

	for (n = 1 ; n < ne ; n++) {
		if (JD < KyuuseiJD[n]) break;
	}
	KJD = KyuuseiJD[n-1];
	KJDF = KyuuseiJDF[n-1];
	ne = KyuuseiJD[n];
	do {
		NKyuusei[0] = KJD;
		KJD += 180;
		if (KJD + 61 > ne) {KJD = ne;}
		if (JD >= KJD) {
			KJDF = (KJDF < 0) ? 1 : -9;
		}
	} while (JD >= KJD);
	NKyuusei[1] = KJD - NKyuusei[0];
	NKyuusei[2] = KJDF;
	return NKyuusei[0];
}

if (!document.layers&&!document.all) event="test"

function showtip2(current,e,text,tips,xing){
	if (document.all&&document.readyState=="complete"){
		var jie1,jie2,jie4,bt,pt,marquee,marx,wt,ht
		if (tips>2){
			jie1='<br>'+Jd2KyuuseiNameL2(tips);
			jie2='<br>'+GetSuku3D(tips*1);
			bt='#FDF5C4';
			pt='120';
			wt='200';
			marx=240;
			ht='200';
			text='<center><b>是日星宿宜忌</b></center><br>'+text
			xing='<br><br>'+xing;
		} else {
			bt='#EBEBEB';
			pt='55';
			jie1='';
			if (tips==1){jie2='<br>'+xing;wt='300';marx=120;ht='170';}
		  else if (tips==-1){jie2='<br>'+xing;wt='300';marx=120;ht='-40';}
			else if(tips==0){jie2='<br>'+month2[xing];wt='300';marx=120;ht='150';}
			else if (tips==2){jie4=tqyb[xing];if(jie4==undefined){text='<center><p>&nbsp;</p><p>对不起！暂无天气数据</p>';ht='-140';}else{text='<table align=center style="font-size: 9pt;"><tr><td align=right><br><b><font color=#FF8000>'+tD1+'/'+ybm1+'<br>'+tD2+'/'+ybm2+'<br>'+tD3+'/'+ybm3+'</td><td>'+jie4+'</td></tr></table>';ht='150';}jie2='';wt='400';marx=200;}
			xing='';
		}
		jie2=jie2.replace(/\n/g,"<br>");
		if (tips!=2){document.all.tip2.innerHTML='<table cellSpacing=1 cellPadding=0 bgColor=#f7f7f7 border=1><tr><td bgColor=#ffffff><table cellSpacing=1 cellPadding=0 bgColor=#e5e5e5 border=0><tr><td bgColor=#ffffff><table cellSpacing=1 cellPadding=0 bgColor=#b2b2b2 border=0><tr><td bgColor=#ffffff><table cellpadding=3 cellspacing=10 bgcolor='+bt+' width='+wt+'><tr><td style="font-size: 9pt" height='+pt+'><font color=#800080>'+text+'</font>'+jie1+'<font color=#800080>'+xing+'</font>'+jie2+'</td></tr></table></td></tr></table></td></tr></table></td></tr></table>';}
		if (tips==2) {
			document.all.tip2.innerHTML='<table cellSpacing=1 cellPadding=0 bgColor=#f7f7f7 border=1><tr><td bgColor=#ffffff><table cellSpacing=1 cellPadding=0 bgColor=#e5e5e5 border=0><tr><td bgColor=#ffffff><table cellSpacing=1 cellPadding=0 bgColor=#b2b2b2 border=0><tr><td bgColor=#ffffff><table cellpadding=3 cellspacing=10 bgcolor='+bt+' width='+wt+'><tr><td style="font-size: 9pt" height='+pt+'>'+text+'</td></tr></table></td></tr></table></td></tr></table></td></tr></table>';
		}
		var tip2x = window.event.clientX-marx;
		if (tip2x < document.body.scrollLeft){tip2x=window.event.clientX+20;}
		var tip2y = window.event.clientY-ht;
		if (tip2y < document.body.scrollTop){tip2y=window.event.clientY+20;}
		if (document.all) {
			tip2x=tip2x + window.document.body.scrollLeft;
			tip2y=tip2y + window.document.body.scrollTop;
		} else {
			tip2x=tip2x + window.pageXOffset;
			tip2y=tip2y + window.pageYOffset;
		}
		tip2.style.pixelLeft=tip2x;
		tip2.style.pixelTop=tip2y;
		document.all.tip2.style.visibility="visible";
	}
}

function hidetip2(){
	if (document.all){
		document.all.tip2.style.visibility="hidden";
	}
	else if (document.layers){
		clearInterval(currentscroll)
		document.tip2.visibility="hidden";
	}
}
