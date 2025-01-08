var moonglk=new Array('孟春','仲春','季春','孟夏','仲夏','季夏','孟秋','仲秋','季秋','孟冬','仲冬','季冬');
var dayglk = new Array('寅','卯','巳','午','巳','午','申','酉','亥','子');
var Kou = new Array(3);
var Sek = new Array(3);
var hour,min;
var nlm = new Array('寅','卯','辰','巳','午','未','申','酉','戌','亥','子','丑');
var Sukuyou = new Array('东方','东方','东方','东方','东方','东方','东方','北方','北方','北方','北方','北方','北方','北方','西方','西方','西方','西方','西方','西方','西方','南方','南方','南方','南方','南方','南方','南方');
var Sukuyou2 = new Array('角木蛟-吉','亢金龙-凶','氐土貉-凶','房日兔-吉','心月狐-凶','尾火虎-吉','箕水豹-吉','斗木獬-吉','牛金牛-凶','女土蝠-凶','虚日鼠-凶','危月燕-凶','室火猪-吉','壁水貐-吉','奎木狼-凶','娄金狗-吉','胃土雉-吉','昴日鸡-凶','毕月乌-吉','觜火猴-凶','参水猿-凶','井木犴-吉','鬼金羊-凶','柳土獐-凶','星日马-凶','张月鹿-吉','翼火蛇-凶','轸水蚓-吉');
var Sukuyou4 = new Array('角','亢','氐','房','心','尾','箕','斗','牛','女','虚','危','室','壁','奎','娄','胃','昴','毕','觜','参','井','鬼','柳','星','张','翼','轸');
var KyuuseiJD = new Array(2404030,2404600,2404810,2408800,2409010,2413000,2413210,2417200,2417410,2421220,2421400,2421610,2425420,2425630,2429620,2429800,2430010,2433820,2434030,2438020,2438230,2442220,2442430,2446420,2446630,2450620,2450830,2454820,2455030,2458840,2459020,2459230,2463250,2467240,2467420,2467630,2471440,2471650,2475640,2475850,2477650);
var KyuuseiJDF= new Array(1,-3,1,7,-9,-3,1,7,-9,7,-3,1,-3,1,7,-3,1,-3,1,7,-9,-3,1,7,-9,-3,1,7,-9,7,-3,1,1,7,-3,1,-3,1,7,-9,-9);
var KyuuseiName = new Array('一白','二黒','三碧','四緑','五黄','六白','七赤','八白','九紫');
var KyuuseiName2= new Array('-太乙星(水)-吉神','-摄提星(土)-凶神','-轩辕星(木)-安神','-招摇星(木)-安神','-天符星(土)-凶神','-青龙星(金)-吉神','-咸池星(金)-凶神','-太阴星(土)-吉神','-天乙星(火)-吉神');
var NKyuusei = new Array(-1,-1,-1);
var Rokuyou = new Array("<font color=#804000>先胜</font>","<font color=#804000>友引</font>","<font color=#804000>先負</font>","<FONT color=#0000A0>佛灭</font>","<FONT color=#FF8C1A>大安</font>","<font color=#804000>赤口</font>");
var NongliData=new Array("19416","19168","42352","21717","53856","55632","21844","22191","39632","21970","19168","42422","42192","53840","53909","46415","54944","44450","38320","18807","18815","42160","46261","27216","27968","43860","11119","38256","21234","18800","25958","54432","59984","27285","23263","11104","34531","37615","51415","51551","54432","55462","46431","22176","42420","9695","37584","53938","43344","46423","27808","46416","21333","19887","42416","17779","21183","43432","59728","27296","44710","43856","19296","43748","42352","21088","62051","55632","23383","22176","38608","19925","19152","42192","54484","53840","54616","46400","46752","38310","38335","18864","43380","42160","45690","27216","27968","44870","43872","38256","19189","18800","25776","29859","59984","27480","23232","43872","38613","37600","51552","55636","54432","55888","30034","22176","43959","9680","37584","51893","43344","46240","47780","44368","21977","19360","42416","20854","21183","43312","31060","27296","44368","23378","19296","42726","42208","53856","60005","54576","23200","30371","38608","19195","19152","42192","53430","53855","54560","56645","46496","22224","21938","18864","42359","42160","43600","45653","27951","44448","19299","37759","18936","18800","25776","26790","59999","27424","42692","43759","37600","53987","51552","54615","54432","55888","23893","22176","42704","21972","21200","43448","43344","46240","46758","44368","21920","43940","42416","21168","45683","26928","29495","27296","44368","19285","19311","42352","21732","53856","59752","54560","55968","27302","22239","19168","43476","42192","53584","62034","54560");
var solarMonth=new Array(31,28,31,30,31,30,31,31,30,31,30,31);
var Gan=new Array("甲","乙","丙","丁","戊","己","庚","辛","壬","癸");
var Gan5=new Array("戊","己","庚","辛","壬","癸","甲","乙","丙","丁");
var sfw=new Array("南","东","北","西","南","东","北","西","南","东","北","西");
var Zhi=new Array("子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥");
var Zhi3=new Array("午","未","申","酉","戌","亥","子","丑","寅","卯","辰","巳");
AnimalIdx=["鼠","牛","虎","兔","龙","蛇","马","羊","猴","鸡","狗","猪"];
AnimalIdx2=["马","羊","猴","鸡","狗","猪","鼠","牛","虎","兔","龙","蛇"];
var Gan3=new Array("甲子 乙丑 丙寅 丁卯 戊辰 己巳 庚午 辛未 壬申 癸酉 甲戌 乙亥","丙子 丁丑 戊寅 己卯 庚辰 辛巳 壬午 癸未 甲申 乙酉 丙戌 丁亥","戊子 己丑 庚寅 辛卯 壬辰 癸巳 甲午 乙未 丙申 丁酉 戊戌 己亥","庚子 辛丑 壬寅 癸卯 甲辰 乙巳 丙午 丁未 戊申 己酉 庚戌 辛亥","壬子 癸丑 甲寅 乙卯 丙辰 丁巳 戊午 己未 庚申 辛酉 壬戌 癸亥","甲子 乙丑 丙寅 丁卯 戊辰 己巳 庚午 辛未 壬申 癸酉 甲戌 乙亥","丙子 丁丑 戊寅 己卯 庚辰 辛巳 壬午 癸未 甲申 乙酉 丙戌 丁亥","戊子 己丑 庚寅 辛卯 壬辰 癸巳 甲午 乙未 丙申 丁酉 戊戌 己亥","庚子 辛丑 壬寅 癸卯 甲辰 乙巳 丙午 丁未 戊申 己酉 庚戌 辛亥","壬子 癸丑 甲寅 乙卯 丙辰 丁巳 戊午 己未 庚申 辛酉 壬戌 癸亥");
var Gan2=new Array('甲不开仓','乙不栽植','丙不修灶','丁不剃头','戊不受田','己不破券','庚不经络','辛不合酱','壬不泱水','癸不词讼');
var Gan4=new Array("<font color=#FF8C1A>金匮</font> <font color=#FF8C1A>天德</font> <FONT color=#0000A0>白虎</font> <font color=#FF8C1A>玉堂</font> <FONT color=#0000A0>天牢</font> <FONT color=#0000A0>玄武</font> <FONT color=#FF8C1A>司命</font> <FONT color=#0000A0>勾陈</font> <font color=#FF8C1A>青龙</font> <font color=#FF8C1A>明堂</font> <FONT color=#0000A0>天刑</font> <FONT color=#0000A0>朱雀</font>","<FONT color=#0000A0>天刑</font> <FONT color=#0000A0>朱雀</font> <font color=#FF8C1A>金匮</font> <font color=#FF8C1A>天德</font> <FONT color=#0000A0>白虎</font> <font color=#FF8C1A>玉堂</font> <FONT color=#0000A0>天牢</font> <FONT color=#0000A0>玄武</font> <FONT color=#FF8C1A>司命</font> <FONT color=#0000A0>勾陈</font> <font color=#FF8C1A>青龙</font> <font color=#FF8C1A>明堂</font>","<font color=#FF8C1A>青龙</font> <font color=#FF8C1A>明堂</font> <FONT color=#0000A0>天刑</font> <FONT color=#0000A0>朱雀</font> <font color=#FF8C1A>金匮</font> <font color=#FF8C1A>天德</font> <FONT color=#0000A0>白虎</font> <font color=#FF8C1A>玉堂</font> <FONT color=#0000A0>天牢</font> <FONT color=#0000A0>玄武</font> <FONT color=#FF8C1A>司命</font> <FONT color=#0000A0>勾陈</font>","<FONT color=#FF8C1A>司命</font> <FONT color=#0000A0>勾陈</font> <font color=#FF8C1A>青龙</font> <font color=#FF8C1A>明堂</font> <FONT color=#0000A0>天刑</font> <FONT color=#0000A0>朱雀</font> <font color=#FF8C1A>金匮</font> <font color=#FF8C1A>天德</font> <FONT color=#0000A0>白虎</font> <font color=#FF8C1A>玉堂</font> <FONT color=#0000A0>天牢</font> <FONT color=#0000A0>玄武</font>","<FONT color=#0000A0>天牢</font> <FONT color=#0000A0>玄武</font> <FONT color=#FF8C1A>司命</font> <FONT color=#0000A0>勾陈</font> <font color=#FF8C1A>青龙</font> <font color=#FF8C1A>明堂</font> <FONT color=#0000A0>天刑</font> <FONT color=#0000A0>朱雀</font> <font color=#FF8C1A>金匮</font> <font color=#FF8C1A>天德</font> <FONT color=#0000A0>白虎</font> <font color=#FF8C1A>玉堂</font>","<FONT color=#0000A0>白虎</font> <font color=#FF8C1A>玉堂</font> <FONT color=#0000A0>天牢</font> <FONT color=#0000A0>玄武</font> <FONT color=#FF8C1A>司命</font> <FONT color=#0000A0>勾陈</font> <font color=#FF8C1A>青龙</font> <font color=#FF8C1A>明堂</font> <FONT color=#0000A0>天刑</font> <FONT color=#0000A0>朱雀</font> <font color=#FF8C1A>金匮</font> <font color=#FF8C1A>天德</font>","<font color=#FF8C1A>金匮</font> <font color=#FF8C1A>天德</font> <FONT color=#0000A0>白虎</font> <font color=#FF8C1A>玉堂</font> <FONT color=#0000A0>天牢</font> <FONT color=#0000A0>玄武</font> <FONT color=#FF8C1A>司命</font> <FONT color=#0000A0>勾陈</font> <font color=#FF8C1A>青龙</font> <font color=#FF8C1A>明堂</font> <FONT color=#0000A0>天刑</font> <FONT color=#0000A0>朱雀</font>","<FONT color=#0000A0>天刑</font> <FONT color=#0000A0>朱雀</font> <font color=#FF8C1A>金匮</font> <font color=#FF8C1A>天德</font> <FONT color=#0000A0>白虎</font> <font color=#FF8C1A>玉堂</font> <FONT color=#0000A0>天牢</font> <FONT color=#0000A0>玄武</font> <FONT color=#FF8C1A>司命</font> <FONT color=#0000A0>勾陈</font> <font color=#FF8C1A>青龙</font> <font color=#FF8C1A>明堂</font>","<font color=#FF8C1A>青龙</font> <font color=#FF8C1A>明堂</font> <FONT color=#0000A0>天刑</font> <FONT color=#0000A0>朱雀</font> <font color=#FF8C1A>金匮</font> <font color=#FF8C1A>天德</font> <FONT color=#0000A0>白虎</font> <font color=#FF8C1A>玉堂</font> <FONT color=#0000A0>天牢</font> <FONT color=#0000A0>玄武</font> <FONT color=#FF8C1A>司命</font> <FONT color=#0000A0>勾陈</font>","<FONT color=#FF8C1A>司命</font> <FONT color=#0000A0>勾陈</font> <font color=#FF8C1A>青龙</font> <font color=#FF8C1A>明堂</font> <FONT color=#0000A0>天刑</font> <FONT color=#0000A0>朱雀</font> <font color=#FF8C1A>金匮</font> <font color=#FF8C1A>天德</font> <FONT color=#0000A0>白虎</font> <font color=#FF8C1A>玉堂</font> <FONT color=#0000A0>天牢</font> <FONT color=#0000A0>玄武</font>","<FONT color=#0000A0>天牢</font> <FONT color=#0000A0>玄武</font> <FONT color=#FF8C1A>司命</font> <FONT color=#0000A0>勾陈</font> <font color=#FF8C1A>青龙</font> <font color=#FF8C1A>明堂</font> <FONT color=#0000A0>天刑</font> <FONT color=#0000A0>朱雀</font> <font color=#FF8C1A>金匮</font> <font color=#FF8C1A>天德</font> <FONT color=#0000A0>白虎</font> <font color=#FF8C1A>玉堂</font>","<FONT color=#0000A0>白虎</font> <font color=#FF8C1A>玉堂</font> <FONT color=#0000A0>天牢</font> <FONT color=#0000A0>玄武</font> <FONT color=#FF8C1A>司命</font> <FONT color=#0000A0>勾陈</font> <font color=#FF8C1A>青龙</font> <font color=#FF8C1A>明堂</font> <FONT color=#0000A0>天刑</font> <FONT color=#0000A0>朱雀</font> <font color=#FF8C1A>金匮</font> <font color=#FF8C1A>天德</font>");
var jcName0 = new Array('建','除','满','平','定','执','破','危','成','收','开','闭');
var jcName1 = new Array('闭','建','除','满','平','定','执','破','危','成','收','开');
var jcName2 = new Array('开','闭','建','除','满','平','定','执','破','危','成','收');
var jcName3 = new Array('收','开','闭','建','除','满','平','定','执','破','危','成');
var jcName4 = new Array('成','收','开','闭','建','除','满','平','定','执','破','危');
var jcName5 = new Array('危','成','收','开','闭','建','除','满','平','定','执','破');
var jcName6 = new Array('破','危','成','收','开','闭','建','除','满','平','定','执');
var jcName7 = new Array('执','破','危','成','收','开','闭','建','除','满','平','定');
var jcName8 = new Array('定','执','破','危','成','收','开','闭','建','除','满','平');
var jcName9 = new Array('平','定','执','破','危','成','收','开','闭','建','除','满');
var jcName10 = new Array('满','平','定','执','破','危','成','收','开','闭','建','除');
var jcName11 = new Array('除','满','平','定','执','破','危','成','收','开','闭','建');
var Zhi2=new Array('子不问卜','丑不冠带','寅不祭祀','卯不穿井','辰不哭泣','巳不远行','午不苫盖','未不服药','申不安床','酉不会客','戌不吃犬','亥不嫁娶');
var Animals=new Array("<a  onmouseout='hidetip2()' onmouseover=showtip2(this,event,'[肖鼠]个性：富幽默感及敏锐的观察力，行事积极，对工作或异性设想周到且细腻，其创见常令人激赏，具敏锐的观察力。','1','&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;特征：年幼时,劳碌奔波中越能发挥其灵敏的智能与耐性;在宽裕的环境中生长,没有失业烦恼,然易见异思迁换工作,中年遇失败後,会一切顺利,尤其能享受晚年财运,须注意罹患肾脏系统疾病。') href='#'>鼠",
"<a  onmouseout='hidetip2()' onmouseover=showtip2(this,event,'[肖牛]个性：富幽默感及敏锐的观察力，行事积极，对工作或异性设想周到且细腻，其创见常令人激赏，具敏锐的观察力。','1','&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;特征：年轻时，在变化多端的环境中，度过操劳的日子，但有坚忍的独立性，年轻时，会为自己的前途散布辛劳的根源。进入中年期，会分为成功大道与沈没于逆境两条路。到四十五、六岁有第二个开拓良好机运的机会，如能抑制唯我独尊的性情，到晚年便能平安。') href='#'>牛",
"<a  onmouseout='hidetip2()' onmouseover=showtip2(this,event,'[肖虎]个性：意志坚强且活跃，为领导人物，但因这些性格遇到困难会受打击而身败名裂。','1','&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;特征：胆大，做事果决，年轻时就会出人头地，中年时若能把握年轻时机运则会成功，若不能把握机运，则会在人际关系中衰败下来，不再成功。寅年出生的人，担任公教人员为宜，且要自爱，不要有贪念与争功的歪念。注意呼吸系统，消化系统的疾病。') href='#'>虎",
"<a  onmouseout='hidetip2()' onmouseover=showtip2(this,event,'[肖兔]个性：做事从容且具幽默感，为受欢迎的社交家。然而，却有急性及见异思迁的缺点.','1','&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;特征：不管任何场合，都能受上司赏识。中年时若投机会埋灭一生，切注意要踏实做事，才能安稳生活。') href='#'>兔",
"<a  onmouseout='hidetip2()' onmouseover=showtip2(this,event,'[肖龙]个性：明朗、活跃的社交家，对事情容易发生兴趣，其缺点是容易热衷也易失去兴趣。','1','&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;特征：大部分与亲人之感情为薄，然在社会上却受欢迎。早年就能发挥潜力，进入中年期需注意无谓的野心，恐怕会身败名裂。若能克服野心，按部就班地照计划完成各项事情，必有最佳回报。最适合的职业为从事教育工作，须注意循环系统的疾病。') href='#'>龙",
"<a  onmouseout='hidetip2()' onmouseover=showtip2(this,event,'[肖蛇]个性：大部分有自我主张，不容易为外人左右。另外，会盲目信赖他人，也是疑心重的人。','1','&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;特征：年轻时，生活在温暖的环境，长大後较懒，所以常换工作，对异性不能专情。中年时，应收敛惰性，立定志向，奋发努力。适宜从事刺激性的工作。') href='#'>蛇",
"<a  onmouseout='hidetip2()' onmouseover=showtip2(this,event,'[肖马]个性：大部分头脑转得快，行动轻敏，有开朗的个性，尊重师长，双亲，性情活泼但又不乏沉稳。','1','&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;特征：是标准的行动派，订立了目标，便努力迈进，年轻时就会露头角，中年若能维持不变，养成耐性才能有所成就，适宜在艺术，教育职位方面求发展。注意心脏系统的疾病。') href='#'>马",
"<a  onmouseout='hidetip2()' onmouseover=showtip2(this,event,'[肖羊]个性：大部分为深思熟虑，研究心强。因此，有神经质且胆怯，做事拖泥带水。','1','&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;特征：虽然年轻时生活安稳，但恐十年代时，会患大疾。中年时是大展鸿图的好机会，因研究心强，若努力研究，能获高的地位，也能致富。特别注意消化器系统的疾病。') href='#'>羊",
"<a  onmouseout='hidetip2()' onmouseover=showtip2(this,event,'[肖猴]个性：才华洋溢，富辩才，进取心强，待人亲切，富同情心。','1','&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;特征：有灵敏的心思，有待人的素养，年少即受人注目，口才灵巧，反应快，年轻时便得好职位。但因有机心，恐会失去良好地位，特别注意一点，较适宜从事推销工作。注意关节方面的疾病。') href='#'>猴",
"<a  onmouseout='hidetip2()' onmouseover=showtip2(this,event,'[肖鸡]个性：大部分为规规矩矩且热心工作，并注重穿着，广交朋友。','1','&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;特征：年轻时，不愿受束缚，喜欢过自由自在的生活方式，因此在工作上无法定心，常换工作，三十岁以後，才会稍微收心。四十至五十岁为黄金时代，应把握时机，好好发挥才能。从事业务方面工作较合适，注意由偏食引起的疾病.') href='#'>鸡",
"<a  onmouseout='hidetip2()' onmouseover=showtip2(this,event,'[肖狗]个性：大都分尽责且保守。因此，较固执，不易接受他人的意见.','1','&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;特征：虽然能够成为团体中的干部，然而，常为了工作上的问题，与上司争议，而丢弃辛苦得来的工作.') href='#'>狗",
"<a  onmouseout='hidetip2()' onmouseover=showtip2(this,event,'[肖猪]个性：大部分为坚定意志者，有爱心及人情味，然而做事有时缺乏考虑。','1','&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;特征：年轻时，对家庭不满，但心里却仍敬爱双亲，性格诚实，自视甚高，常吃亏。亥年出生的晚年都很幸福，中年以後能慢慢发挥才能。需注意呼吸及消化系统的疾病。') href='#'>猪");
var solarTerm = new Array("小寒","大寒","立春","雨水","惊蛰","春分","清明","谷雨","立夏","小满","芒种","夏至","小暑","大暑","立秋","处暑","白露","秋分","寒露","霜降","立冬","小雪","大雪","冬至[冬节]");
var mla = new Array(481267.8809,218.3162,6.2888,1.2740,0.6583,0.2136,0.1851,0.1144,0.0588,0.0571,0.0533,0.0458,0.0409,0.0347,0.0304,0.0154,0.0125,0.0110,0.0107,0.0100,0.0085,0.0079,0.0068,0.0052,0.0050,0.0040,0.0040,0.0040,0.0038,0.0037,0.0028,0.0027,0.0026,0.0024,0.0023,0.0022,0.0021,0.0021,0.0021,0.0018,0.0016,0.0012,0.0011,0.0009,0.0008,0.0007,0.0007,0.0007,0.0007,0.0006,0.0006,0.0005,0.0005,0.0005,0.0004,0.0004,0.0003,0.0003,0.0003,0.0003,0.0003,0.0003,0.0003);
var mlb = new Array(0,0,477198.868,413335.35,890534.22,954397.74,35999.05,966404,63863.5,377336.3,1367733.1,854535.2,441199.8,445267.1,513197.9,75870,1443603,489205,1303870,1431597,826671,449334,926533,31932,481266,1331734,1844932,133,1781068,541062,1934,918399,1379739,99863,922466,818536,990397,71998,341337,401329,1856938,1267871,1920802,858602,1403732,790672,405201,485333,27864,111869,2258267,1908795,1745069,509131,39871,12006,958465,381404,349472,1808933,549197,4067,2322131);
var mlc = new Array(0,0,44.963,10.74,145.7,179.93,87.53,276.5,124.2,13.2,280.7,148.2,47.4,27.9,222.5,41,52,142,246,315,111,188,323,107,205,283,56,29,21,259,145,182,17,122,163,151,357,85,16,274,152,249,186,129,98,114,50,186,127,38,156,90,24,242,223,187,340,354,337,58,220,70,191);
var maa = new Array(5.1281,0.2806,0.2777,0.1733,0.0554,0.0463,0.0326,0.0172,0.0093,0.0088,0.0082,0.0043,0.0042,0.0034,0.0025,0.0022,0.0022,0.0021,0.0019,0.0018,0.0018,0.0018,0.0015,0.0015,0.0015,0.0014,0.0013,0.0013,0.0011,0.0010,0.0009,0.0008,0.0007,0.0006,0.0006,0.0005,0.0005,0.0005,0.0004,0.0004,0.0003,0.0003,0.0003,0.0003,0.0003);
var mab = new Array(483202.019,960400.89,6003.15,407332.2,896537.4,69866.7,1373736.2,1437599.8,884531,471196,371333,547066,1850935,443331,860538,481268,1337737,105866,924402,820668,519201,1449606,42002,928469,996400,29996,447203,37935,1914799,1297866,1787072,972407,1309873,559072,1361730,848352,419339,948395,2328134,1024264,932536,1409735,2264270,1814936,335334);
var mac = new Array(3.273,138.24,48.31,52.43,104,82.5,239,273.2,187,87,55,217,14,230,106,308,241,80,141,153,181,10,46,121,316,129,6,65,48,288,340,235,205,134,322,190,149,222,149,352,282,57,115,16,57);
var mha = new Array(0.950725,0.051820,0.009530,0.007842,0.002824,0.000858,0.000531,0.000400,0.000319,0.000271,0.000263,0.000197,0.000173,0.000167,0.000111,0.000103,0.000084,0.000083,0.000078,0.000073,0.000064,0.000063,0.000041,0.000034,0.000033,0.000031,0.000030,0.000029,0.000026,0.000023,0.000019,0.000013,0.000013,0.000013,0.000012,0.000011,0.000011,0.000010,0.000009,0.000007,0.000007,0.000006,0.000006,0.000005);
var mhb = new Array(0,477198.868,413335.35,890534.22,954397.74,1367733.1,854535.2,377336.3,441199.8,445267,513198,489205,1431597,1303870,35999,826671,63864,926533,1844932,1781068,1331734,449334,481266,918399,541062,922466,75870,990397,818536,553069,1267871,1403732,341337,2258267,2258267,1908795,858602,1745069,790672,2322131,1808933,485333,99863,405201);
var mhc = new Array(0,134.963,100.74,235.7,269.93,10.7,238.2,103.2,137.4,118,312,232,45,336,178,201,214,53,146,111,13,278,295,272,349,253,131,87,241,266,339,188,106,246,246,180,219,114,204,281,148,276,212,140);
var sTermInfo = new Array(0,21208,42467,63836,85337,107014,128867,150921,173149,195551,218072,240693,263343,285989,308563,331033,353350,375494,397447,419210,440795,462224,483532,504758);
var nStr1 = new Array('日','一','二','三','四','五','六','七','八','九','十');
var nStr2 = new Array('初','十','廿','卅','　');
var monthName = new Array("<a  onmouseout='hidetip2()' onmouseover=showtip2(this,event,'12月22日-1月20日','-1','★山羊座(摩羯座)★\\n　　优点：做事脚踏实地，意志力强，有家庭观念，对人谦逊，处处谨慎....&nbsp;。\\n　　缺点：固执,不够乐观,个人利己主义,缺乏浪漫情趣,太专注於个人的目标。\\n1月22日-2月21日　★水瓶座★　请看下月...') href='#'>JAN",
"<a  onmouseout='hidetip2()' onmouseover=showtip2(this,event,'1月22日-2月21日','-1','★水瓶座★\\n　　优点：崇尚自由，兴趣广泛，创意十足，有理性的智慧，感情忠实。\\n　　缺点：缺乏热情，过於强调生活的自主权，太过理智情趣不足，多管闲事。\\n2月22日-3月21日　★双鱼座★　　请看下月...') href='#'>FEB",
"<a  onmouseout='hidetip2()' onmouseover=showtip2(this,event,'2月22日-3月21日','-1','★双鱼座★\\n　　优点：感情丰富，心地仁慈，舍己为人，不自私，懂得包容，温和且浪漫。\\n　　缺点：不够实际，太情绪化，多愁善感，不善理财，感情用事。\\n3月22日-4月20日　★白羊座★　请看下月...') href='#'>MAR",
"<a  onmouseout='hidetip2()' onmouseover=showtip2(this,event,'3月22日-4月20日','-1','★白羊座★\\n　　优点：做事积极，热情有活力，有明快的决断力，坦白率真，重情讲义气。\\n　　缺点：自我中心太强，粗心大意，容易脑羞成怒，缺乏时间观念缺乏耐性。\\n4月21日-5月21日　★金牛座★　请看下月...') href='#'>APR",
"<a  onmouseout='hidetip2()' onmouseover=showtip2(this,event,'4月21日-5月21日','-1','★金牛座★\\n　　优点：耐性十足，脚踏实地，一往情深，有艺术天份，做事有计划有规律。\\n　　缺点：占有欲太强，善妒，缺乏幽默感，不知变通，缺乏求新求变的勇气。\\n5月22日-6月21日　★双子座★　请看下月...') href='#'>MAY",
"<a  onmouseout='hidetip2()' onmouseover=showtip2(this,event,'5月22日-6月21日','-1','★双子座★\\n　　优点：有高人一等的幽默感，有天生的语言才华，反应较快，学习能力强。\\n　　缺点：付出少却要得多，喜欢批评别人而不检讨自己，做任何事都欠耐心。\\n6月22日-7月21日　★巨蟹座★　请看下月...') href='#'>JUN",
"<a  onmouseout='hidetip2()' onmouseover=showtip2(this,event,'6月22日-7月21日','-1','★巨蟹座★\\n　　优点：情感真挚深切，想像力丰富，念旧重情，懂得体贴关怀，善解人意。\\n　　缺点：提不起放不下，说话拐弯抹角，不直接，不知适可而止，缺乏理性。\\n7月22日-8月21日　★狮子座★　请看下月...') href='#'>JUL",
"<a  onmouseout='hidetip2()' onmouseover=showtip2(this,event,'7月22日-8月21日','-1','★狮子座★\\n　　优点：一言九鼎有信用，乐观，心胸宽大，懂得宽恕具有激励人心的气质。\\n　　缺点：死爱面子活受罪，缺乏节俭的美德，刚愎自用，自以为是缺乏耐性。\\n8月22日-9月21日　★处女座★　请看下月...') href='#'>AUG",
"<a  onmouseout='hidetip2()' onmouseover=showtip2(this,event,'8月22日-9月21日','-1','★处女座★\\n　　优点：追求完美谦逊不夸大，有精确的观察力，对爱情忠实守本份有耐性。\\n　　缺点：太过吹毛求疵，有洁癖顷向，不够浪漫不尊重他人的梦想，欠远见。\\n9月22日-10月21日　★天秤座★　请看下月...') href='#'>SEP",
"<a  onmouseout='hidetip2()' onmouseover=showtip2(this,event,'9月22日-10月21日','-1','★天秤座★\\n　　优点：公平客观，天生的优雅风采，浪漫的恋爱高手，能屈能伸适应力强。\\n　　缺点：优柔寡断，犹豫不决，总是自愿其说，藉囗太多，爱享受好逸恶劳。\\n10月22日-11月21日　★天蝎座★　请看下月...') href='#'>OCT",
"<a  onmouseout='hidetip2()' onmouseover=showtip2(this,event,'10月22日-11月21日','-1','★天蝎座★\\n　　优点：对朋友讲义气，天生的性感魅力，对人生有潜在的热情，恩怨分明。\\n　　缺点：占有欲过高，爱吃醋，得理不饶人，囗是心非，城府太深报复心强。\\n11月22日-12月21日　★人马座（射手座）★　请看下月...') href='#'>NOV",
"<a  onmouseout='hidetip2()' onmouseover=showtip2(this,event,'11月22日-12月21日','-1','★人马座（射手座）★\\n　　优点：天生乐观，正直坦率，有救世救人的热情，待人友善，经得起打击。\\n　　缺点：心直囗快，容易得罪人，不信邪，不听劝告，冲动，不懂三思而行。\\n12月22日-1月20日　★山羊座(摩羯座)　★请看1月...') href='#'>DEC");
var month2=new Array("近代史上的元月：<br><br>《1789年1月----美国第一次大选,华盛顿当选为第一任总统》<br>《1912年1月1日----中华民国成立，孙中山任临时大总统，同期国民党成立》<br>《1924年1月----第一次国共合作》<br>《1932年1月28日----日本在上海发动“一.二八”事变》<br>《1933年1月----德国希特拉上台》<br>《1949年1月----淮海战役、平津战役解放军取得最后胜利》<br>《1976年1月8日----周恩来逝世》",
"近代史上的2月：<br><br>《1848年2月----“共产党宣言”在欧洲发表》<br>《1848年2月----法国“二月革命”爆发》<br>《1923年2月----京汉铁路工人大罢工》<br>《1972年2月21日----尼克松到访中国》",
"近代史上的3月：<br>《1925年3月12日----孙中山逝世》<br>《1932年3月----日本扶植清朝废帝溥仪，在东北建立伪“满洲国”》",
"近代史上的4月：<br>《1861年4月----美国南北战争爆发》<br>《1911年4月----广州黄花冈起义》<br>《1949年4月23日----解放军解放南京,民国结束》<br>《1970年4月24日----中国成功发射第一颗人造卫星》<br>《1976年4月7日----华国锋任中共第一副主席》<br>《1984年4月6日----中国对外开放14个沿海城市》",
"近代史上的5月：<br>《1841年5月29日----广州三元里民众抵抗英军入侵》<br>《1919年5月4日----“五四”运动爆发》<br>《1945年5月8日----德国签订无条件投降书》",
"近代史上的6月：<br>《1840年6月----第一次鸦片战争爆发》<br>《1898年6月----“百日维新”开始》<br>《1913年6月----国民二次革命开始》<br>《1914年6月28日----萨拉热窝事件--第一次世界大战爆发》<br>《1944年6月6日----英美盟军登陆诺曼底》<br>《1950年6月25日----朝鲜战争爆发》",
"近代史上的7月：<br>《1776年7月4日----《独立宣言》发表，美国成立》<br>《1921年7月----中国共产党成立》<br>《1937年7月7日----日本发动芦沟桥事变》<br>《1946年7月----中国爆发全面内战》<br>《1953年7月26日----朝鲜战争结束，形成南北对峙局面》<br>《1976年7月6日----朱德逝世》<br>《1997年7月----中国收回香港主权》",
"近代史上的8月：<br>《1789年8月----法国《人权宣言》发布》<br>《1842年8月----清政府割让香港》<br>《1894年8月1日----清政府对日宣战，甲午战争爆发》<br>《1905年8月----中国同盟会成立》<br>《1927年8月1日----“八一”南昌起义》<br>《1945年8月6日、9日----美国在日本广岛、长崎第一次使用原子弹》<br>《1945年8月14日----日本宣布无条件投降》<br>《1966年8月18日----毛泽东在天安门接见红卫兵》",
"近代史上的9月：<br>《1909年9月----中国第一条自行设计的“京张”铁路开通》<br>《1915年9月----中国新文化运动开始》<br>《1922年9月----安源路矿工人大罢工》<br>《1931年9月18日----日本在沈阳发动“九.一八”事变》<br>《1939年9月3日第二次世界大战全面爆发》<br>《1945年9月2日----日本签订无条件投降书》<br>《1976年9月9日----毛泽东逝世》<br>《1984年9月26日----中英发表关于香港问题的联合声明》",
"近代史上的10月：<br>《1856年10月----英法联军火烧圆明园；同月清政府割让九龙半岛》<br>《1911年10月10日----武昌起义，辛亥革命开始》<br>《1934年10月----红军开始二万五千里长征》<br>《1949年10月1日----中华人民共和国成立》<br>《1950年10月25日----中国人民志愿军入朝参加抗美战争》<br>《1951年10月26日----解放军进藏，西藏和平解放》<br>《1957年10月4日----前苏联将世界上第一颗人造卫星送上太空》<br>《1976年10月6日----“四人帮”受审》",
"近代史上的11月：<br>《1798年11月9日----法国拿破伦发动“雾月政变”，拿破伦时代开始》<br>《1917年11月7日----“十月革命”胜利苏联成立》<br>《1943年11月----苏、美、英三大巨头举行德克兰会议》<br>《1948年11月----辽沈战役结束,淮海战役开始》<br>《1969年11月12日----刘少奇在开封逝世》",
"近代史上的12月：<br>《1936年12月12日----西安事变》<br>《1937年12月13日----南京大屠杀》<br>《1941年12月8日----太平洋战争爆发》");
var sFtv = new Array( "0101*元旦", "0214 情人节", "0308 妇女节", "0312 植树节", "0315 消费者权益日", "0401 愚人节", "0501 劳动节", "0504 青年节", "0512 护士节", "0601 儿童节", "0701 建党节 香港回归纪念日", "0801 建军节", "0909 毛泽东逝世纪念日", "0910 教师节", "0928 孔子诞辰", "1001*国庆节", "1006 老人节", "1024 联合国日", "1031 万圣节（鬼节）", "1112 孙中山诞辰纪念日", "1220 澳门回归纪念日", "1224*平安夜", "1225*圣诞节", "1226 毛泽东诞辰纪念日");
var lFtv = new Array( "0101*春节", "0107*人日", "0115*元宵节", "0125 填仓节", "0126 生菜会", "0202 龙头节", "0206 东华帝君诞", "0215 涅槃节", "0219 观音诞", "0323 妈祖诞、天后诞", "0408 牛王诞", "0505*端午节", "0508 龙母诞", "0520 分龙节", "0606 姑姑节", "0616 鲁班节", "0624 关帝节", "0630 围香节","0707 七夕情人节", "0715 中元节(鬼节)", "0802 灶君诞", "0827 先师诞", "0815*中秋节", "0909 重阳节", "1001 祭祖节、祀靴节", "1025 感天上帝诞", "1208 腊八节", "1220 鲁班公诞", "1224 小年（祀灶）", "0100*除夕");
var wFtv = new Array( "0231 总统日","0520 母亲节", "0531 胜利日", "0630 父亲节", "0716 合作节", "0730 被奴役国家周", "0911 西方劳动节", "1011 世界住房日", "1021 美国哥伦布纪念日", "1144 感恩节");

function lYearDays(y) {
	var i, sum = 348;
	for(i=0x8000; i>0x8; i>>=1) sum += (NongliData[y-1900] & i)? 1: 0;
	return(sum+leapDays(y));
}

function lYearDays(y) {
	var i, sum = 348;
	for(i=0x8000; i>0x8; i>>=1) sum += (NongliData[y-1900] & i)? 1: 0;
	return(sum+leapDays(y));
}

function leapDays(y) {
	if(leapMonth(y)) return( (NongliData[y-1899]&0xf)==0xf? 30: 29);
	else return(0);
}

function leapMonth(y) {
	var lm = NongliData[y-1900] & 0xf;
	return(lm==0xf?0:lm);
}

function monthDays(y,m) {
	return( (NongliData[y-1900] & (0x10000>>m))? 30: 29 )
}

function Lunar(objDate) {
	var i, leap=0, temp=0;
	var offset   = (Date.UTC(objDate.getFullYear(),objDate.getMonth(),objDate.getDate()) - Date.UTC(1900,0,31))/86400000;
	for(i=1900; i<2100 && offset>0; i++) {
		temp=lYearDays(i); offset-=temp;
	}
	if(offset<0) {
		offset+=temp; i--;
	}
	this.year = i;
	leap = leapMonth(i);
	this.isLeap = false;
	for(i=1; i<13 && offset>0; i++) {
		if(leap>0 && i==(leap+1) && this.isLeap==false) {
			--i;
			this.isLeap = true;
			temp = leapDays(this.year);
		} else {
			temp = monthDays(this.year, i);
		}
		if(this.isLeap==true && i==(leap+1)) this.isLeap = false;
		offset -= temp;
	}
	if(offset==0 && leap>0 && i==leap+1)
	if(this.isLeap){
		this.isLeap = false;
	} else {
		this.isLeap = true; --i;
	}
	if(offset<0){
		offset += temp; --i;
	}
	this.month = i;
	this.day = offset + 1;
}

function solarDays(y,m) {
	if(m==1)
		return(((y%4 == 0) && (y%100 != 0) || (y%400 == 0))? 29: 28)
	else
		return(solarMonth[m])
}

function cyclical(num) {
	return(Gan[num%10]+Zhi[num%12])
}
function cyclical4(num) {
	return(Gan3[num%10])
}
function cyclical5(num) {
	return(Gan4[num%12])
}
function cyclical3(num) {
	return('<font color="#804000">彭祖百忌：</font>['+Gan2[num%10]+' '+Zhi2[num%12]+']')
}

function calElement(sYear,sMonth,sDay,week,lYear,lMonth,lDay,isLeap,cYear,cMonth,cDay) {
      this.isToday    = false;

      this.sYear      = sYear;
      this.sMonth     = sMonth;
      this.sDay       = sDay;
      this.week       = week;

      this.lYear      = lYear;
      this.lMonth     = lMonth;
      this.lDay       = lDay;
      this.isLeap     = isLeap;

      this.cYear      = cYear;
      this.cMonth     = cMonth;
      this.cDay       = cDay;

      this.color      = '';
      this.solarTerms    = '';
      this.solarFestival = '';
      this.lunarFestival = '';
}

function sTerm(y,n) {
   var offDate = new Date( ( 31556925974.7*(y-1900) + sTermInfo[n]*60000  ) + Date.UTC(1900,0,6,2,5) )
   return(offDate.getUTCDate())
}

function cyclical6(num,num2) {
	if (num==0) return(jcName0[num2]);
	if (num==1) return(jcName1[num2]);
	if (num==2) return(jcName2[num2]);
	if (num==3) return(jcName3[num2]);
	if (num==4) return(jcName4[num2]);
	if (num==5) return(jcName5[num2]);
	if (num==6) return(jcName6[num2]);
	if (num==7) return(jcName7[num2]);
	if (num==8) return(jcName8[num2]);
	if (num==9) return(jcName9[num2]);
	if (num==10) return(jcName10[num2]);
	if (num==11) return(jcName11[num2]);
}

function calendar(y,m) {

   var sDObj, lDObj, lY, lM, lD=1, lL, lX=0, tmp1, tmp2,lM2,lY2,lD2,tmp3,dayglus,bsg,xs,xs1,fs,fs1,cs,cs1
   var lDPOS = new Array(3)
   var n = 0
   var firstLM = 0

   sDObj = new Date(y,m,1,0,0,0,0);
   this.length    = solarDays(y,m)
   this.firstWeek = sDObj.getDay()
	if(m<2) {
		cY=cyclical(y-1900+36-1);lY2=(y-1900+36-1);
	} else {
		cY=cyclical(y-1900+36);lY2=(y-1900+36);
	}
	var term2=sTerm(y,2);
	var firstNode = sTerm(y,m*2)
	cM = cyclical((y-1900)*12+m+12);
	lM2= (y-1900)*12+m+12;
	var dayCyclical = Date.UTC(y,m,1,0,0,0,0)/86400000+25567+10;

	for(var i=0;i<this.length;i++) {
		if(lD>lX) {
			sDObj = new Date(y,m,i+1)
			lDObj = new Lunar(sDObj)
			lY    = lDObj.year
			lM    = lDObj.month
			lD    = lDObj.day
			lL    = lDObj.isLeap
			lX    = lL? leapDays(lY): monthDays(lY,lM)
			if(n==0) firstLM = lM
			lDPOS[n++] = i-lD+1
		}
		if(m==1 && (i+1)==term2){
			cY=cyclical(y-1900+36);
			lY2=(y-1900+36);
		}
		if((i+1)==firstNode) {
			cM = cyclical((y-1900)*12+m+13);
			lM2=(y-1900)*12+m+13;
		}
		cD = cyclical(dayCyclical+i);
		lD2=(dayCyclical+i);
		this[i] = new calElement(y, m+1, i+1, nStr1[(i+this.firstWeek)%7], lY, lM, lD++, lL, cY ,cM, cD );
		bsg=(lD2)%12;
		cs1=i+1;
		if(m==0){
			if(cs1<sTerm(y,m*2  )-1){xs1='水泉动';}
			else if(cs1>=sTerm(y,m*2  )-1 && cs1<=sTerm(y,m*2  )+3) {xs1='雁北乡';}
			else if(cs1>sTerm(y,m*2  )+3 && cs1<=sTerm(y,m*2  )+8) {xs1='鹊始巢';}
			else if(cs1>sTerm(y,m*2  )+8 && cs1<sTerm(y,m*2+1)-1){xs1='鳺始鴝';}
			else if(cs1>=sTerm(y,m*2+1)-1 && cs1<=sTerm(y,m*2+1)+3){xs1='鸡始乳';}
			else if(cs1>sTerm(y,m*2+1)+3 && cs1<=sTerm(y,m*2+1)+8){xs1='征鸟厉疾';}
			else if(cs1>sTerm(y,m*2+1)+8){xs1='水泽腹坚';}
		}
		if(m==1){
			if(cs1<sTerm(y,m*2  )-1){xs1='水泽腹坚';}
			else if(cs1>=sTerm(y,m*2  )-1 && cs1<=sTerm(y,m*2  )+3){xs1='东风解冻';}
			else if(cs1>sTerm(y,m*2  )+3 && cs1<=sTerm(y,m*2  )+8){xs1='蛰虫始振';}
			else if(cs1>sTerm(y,m*2  )+8 && cs1<sTerm(y,m*2+1)-1){xs1='鱼上冰';}
			else if(cs1>=sTerm(y,m*2+1)-1 && cs1<=sTerm(y,m*2+1)+3){xs1='獭祭鱼';}
			else if(cs1>sTerm(y,m*2+1)+3 && cs1<=sTerm(y,m*2+1)+8){xs1='候雁北';}
			else if(cs1>sTerm(y,m*2+1)+8){xs1='草木萌动';}
		}
		if(m==2){
			if(cs1<sTerm(y,m*2  )-1){xs1='草木萌动';}
			else if(cs1>=sTerm(y,m*2  )-1 && cs1<=sTerm(y,m*2  )+3){xs1='桃始华';}
			else if(cs1>sTerm(y,m*2  )+3 && cs1<=sTerm(y,m*2  )+8){xs1='仓庚鸣';}
			else if(cs1>sTerm(y,m*2  )+8 && cs1<sTerm(y,m*2+1)-1){xs1='鹰化为鸠';}
			else if(cs1>=sTerm(y,m*2+1)-1 && cs1<=sTerm(y,m*2+1)+3){xs1='玄鸟至';}
			else if(cs1>sTerm(y,m*2+1)+3 && cs1<=sTerm(y,m*2+1)+8){xs1='雷乃发声';}
			else if(cs1>sTerm(y,m*2+1)+8){xs1='始电';}
		}
		if(m==3){
			if(cs1<sTerm(y,m*2  )-1){xs1='始电';}
			else if(cs1>=sTerm(y,m*2  )-1 && cs1<=sTerm(y,m*2  )+3){xs1='桐始华';}
			else if(cs1>sTerm(y,m*2  )+3 && cs1<=sTerm(y,m*2  )+8){xs1='田鼠化为鴽';}
			else if(cs1>sTerm(y,m*2  )+8 && cs1<sTerm(y,m*2+1)-1){xs1='虹始见';}
			else if(cs1>=sTerm(y,m*2+1)-1 && cs1<=sTerm(y,m*2+1)+3){xs1='萍始生';}
			else if(cs1>sTerm(y,m*2+1)+3 && cs1<=sTerm(y,m*2+1)+8){xs1='鸣鸠拂其羽';}
			else if(cs1>sTerm(y,m*2+1)+8){xs1='戴胜降于桑';}
		}
		if(m==4){
			if(cs1<sTerm(y,m*2  )-1){xs1='戴胜降于桑';}
			else if(cs1>=sTerm(y,m*2  )-1 && cs1<=sTerm(y,m*2  )+3){xs1='蝼蝈鸣';}
			else if(cs1>sTerm(y,m*2  )+3 && cs1<=sTerm(y,m*2  )+8){xs1='蚯蚓出';}
			else if(cs1>sTerm(y,m*2  )+8 && cs1<sTerm(y,m*2+1)-1){xs1='王瓜生';}
			else if(cs1>=sTerm(y,m*2+1)-1 && cs1<=sTerm(y,m*2+1)+3){xs1='苦菜秀';}
			else if(cs1>sTerm(y,m*2+1)+3 && cs1<=sTerm(y,m*2+1)+8){xs1='靡草死';}
			else if(cs1>sTerm(y,m*2+1)+8){xs1='麦秋至';}
		}
		if(m==5){
			if(cs1<sTerm(y,m*2  )-1){xs1='麦秋至';}
			else if(cs1>=sTerm(y,m*2  )-1 && cs1<=sTerm(y,m*2  )+3){xs1='螳螂生';}
			else if(cs1>sTerm(y,m*2  )+3 && cs1<=sTerm(y,m*2  )+8){xs1='鵙始鸣';}
			else if(cs1>sTerm(y,m*2  )+8 && cs1<sTerm(y,m*2+1)-1){xs1='反舌无声';}
			else if(cs1>=sTerm(y,m*2+1)-1 && cs1<=sTerm(y,m*2+1)+3){xs1='鹿角解';}
			else if(cs1>sTerm(y,m*2+1)+3 && cs1<=sTerm(y,m*2+1)+8){xs1='蜩始鸣';}
			else if(cs1>sTerm(y,m*2+1)+8){xs1='半夏生';}
		}
		if(m==6){
			if(cs1<sTerm(y,m*2  )-1){xs1='半夏生';}
			else if(cs1>=sTerm(y,m*2  )-1 && cs1<=sTerm(y,m*2  )+3){xs1='温风至';}
			else if(cs1>sTerm(y,m*2  )+3 && cs1<=sTerm(y,m*2  )+8){xs1='蟀蟋居壁';}
			else if(cs1>sTerm(y,m*2  )+8 && cs1<sTerm(y,m*2+1)-1){xs1='鹰如鸷';}
			else if(cs1>=sTerm(y,m*2+1)-1 && cs1<=sTerm(y,m*2+1)+3){xs1='腐草为萤';}
			else if(cs1>sTerm(y,m*2+1)+3 && cs1<=sTerm(y,m*2+1)+8){xs1='土润溽暑';}
			else if(cs1>sTerm(y,m*2+1)+8){xs1='大雨时行';}
		}
		if(m==7){
			if(cs1<sTerm(y,m*2  )-1){xs1='大雨时行';}
			else if(cs1>=sTerm(y,m*2  )-1 && cs1<=sTerm(y,m*2  )+3){xs1='凉风至';}
			else if(cs1>sTerm(y,m*2  )+3 && cs1<=sTerm(y,m*2  )+8){xs1='白露降';}
			else if(cs1>sTerm(y,m*2  )+8 && cs1<sTerm(y,m*2+1)-1){xs1='寒蝉鸣';}
			else if(cs1>=sTerm(y,m*2+1)-1 && cs1<=sTerm(y,m*2+1)+3){xs1='鹰乃祭鸟';}
			else if(cs1>sTerm(y,m*2+1)+3 && cs1<=sTerm(y,m*2+1)+8){xs1='天地始肃';}
			else if(cs1>sTerm(y,m*2+1)+8){xs1='禾乃登';}
		}
		if(m==8){
			if(cs1<sTerm(y,m*2  )-1){xs1='禾乃登';}
			else if(cs1>=sTerm(y,m*2  )-1 && cs1<=sTerm(y,m*2  )+3){xs1='鸿雁来';}
			else if(cs1>sTerm(y,m*2  )+3 && cs1<=sTerm(y,m*2  )+8){xs1='玄鸟归';}
			else if(cs1>sTerm(y,m*2  )+8 && cs1<sTerm(y,m*2+1)-1){xs1='群鸟养羞';}
			else if(cs1>=sTerm(y,m*2+1)-1 && cs1<=sTerm(y,m*2+1)+3){xs1='雷乃收声';}
			else if(cs1>sTerm(y,m*2+1)+3 && cs1<=sTerm(y,m*2+1)+8){xs1='蛰虫坯户';}
			else if(cs1>sTerm(y,m*2+1)+8){xs1='水始涸';}
		}
		if(m==9){
			if(cs1<sTerm(y,m*2  )-1){xs1='水始涸';}
			else if(cs1>=sTerm(y,m*2  )-1 && cs1<=sTerm(y,m*2  )+3){xs1='鸿雁来宾';}
			else if(cs1>sTerm(y,m*2  )+3 && cs1<=sTerm(y,m*2  )+8){xs1='雀入大水为蛤';}
			else if(cs1>sTerm(y,m*2  )+8 && cs1<sTerm(y,m*2+1)-1){xs1='菊有黄花';}
			else if(cs1>=sTerm(y,m*2+1)-1 && cs1<=sTerm(y,m*2+1)+3){xs1='豺乃祭兽';}
			else if(cs1>sTerm(y,m*2+1)+3 && cs1<=sTerm(y,m*2+1)+8){xs1='草木黄落';}
			else if(cs1>sTerm(y,m*2+1)+8){xs1='蛰虫咸俯';}
		}
		if(m==10){
			if(cs1<sTerm(y,m*2  )-1){xs1='蛰虫咸俯';}
			else if(cs1>=sTerm(y,m*2  )-1 && cs1<=sTerm(y,m*2  )+3){xs1='水始冰';}
			else if(cs1>sTerm(y,m*2  )+3 && cs1<=sTerm(y,m*2  )+8){xs1='地始冻';}
			else if(cs1>sTerm(y,m*2  )+8 && cs1<sTerm(y,m*2+1)-1){xs1='雉入大水为蜃';}
			else if(cs1>=sTerm(y,m*2+1)-1 && cs1<=sTerm(y,m*2+1)+3){xs1='虹藏不见';}
			else if(cs1>sTerm(y,m*2+1)+3 && cs1<=sTerm(y,m*2+1)+8){xs1='天气腾地气降';}
			else if(cs1>sTerm(y,m*2+1)+8){xs1='闭塞成冬';}
		}
		if(m==11){
			if(cs1<sTerm(y,m*2  )-1){xs1='闭塞成冬';}
			else if(cs1>=sTerm(y,m*2  )-1 && cs1<=sTerm(y,m*2  )+3){xs1='鹖鴠不鸣';}
			else if(cs1>sTerm(y,m*2  )+3 && cs1<=sTerm(y,m*2  )+8){xs1='虎始交';}
			else if(cs1>sTerm(y,m*2  )+8 && cs1<sTerm(y,m*2+1)-1){xs1='荔挺出';}
			else if(cs1>=sTerm(y,m*2+1)-1 && cs1<=sTerm(y,m*2+1)+3){xs1='蚯蚓结';}
			else if(cs1>sTerm(y,m*2+1)+3 && cs1<=sTerm(y,m*2+1)+8){xs1='麋鹿解';}
			else if(cs1>sTerm(y,m*2+1)+8){xs1='水泉动';}
		}
		if(bsg==0){dayglus=Gan[9]+'命进禄 ';}
		else if(bsg==2){dayglus=Gan[0]+'命进禄 ';}
		else if(bsg==3){dayglus=Gan[1]+'命进禄 ';}
		else if(bsg==5){dayglus=Gan[2]+','+Gan[4]+'命进禄 ';}
		else if(bsg==6){dayglus=Gan[3]+','+Gan[5]+'命进禄 ';}
		else if(bsg==8){dayglus=Gan[6]+'命进禄 ';}
		else if(bsg==9){dayglus=Gan[7]+'命进禄 ';}
		else if(bsg==11){dayglus=Gan[8]+'命进禄 ';}
		else {dayglus='';}
		if((lD2)%10==0 || (lD2)%10==5){xs='东北';}
		else if((lD2)%10==1 || (lD2)%10==6){xs='西北';}
		else if((lD2)%10==2 || (lD2)%10==7){xs='西南';}
		else if((lD2)%10==3 || (lD2)%10==8){xs='正南';}
		else if((lD2)%10==4 || (lD2)%10==9){xs='东南';}
		if((lD2)%10==0 || (lD2)%10==1){fs='东南';}
		else if((lD2)%10==2 || (lD2)%10==3){fs='正东';}
		else if((lD2)%10==4){fs='正北';}
		else if((lD2)%10==5){fs='正南';}
		else if((lD2)%10==6 || (lD2)%10==7){fs='西南';}
		else if((lD2)%10==8){fs='西北';}
		else if((lD2)%10==9){fs='正西';}
		if((lD2)%10==0 || (lD2)%10==1){cs='东北';}
		else if((lD2)%10==2 || (lD2)%10==3){cs='西南';}
		else if((lD2)%10==4 || (lD2)%10==5){cs='正北';}
		else if((lD2)%10==6 || (lD2)%10==7){cs='正东';}
		else if((lD2)%10==8 || (lD2)%10==9){cs='正南';}
		this[i].pgday =cyclical3(lD2);
		this[i].dGz = '时辰：'+cyclical4(lD2);
		this[i].sgz ='<FONT color=#FF8C1A>吉</font><FONT color=#0000A0>凶</font>：'+ cyclical5(lD2);
		this[i].sgz2 =jzny([lD2]%10+''+[lD2]%12);
		this[i].sgz4 =CalConv((lD2)%10,(lD2)%12);
		this[i].sgz5 =CalConv2(lY2%12,lM2%12,(lD2)%12,lY2%10,(lD2)%10,lM,lD-1,m+1,cs1);
		this[i].sgz6 =cyclical7(lM2%12,(lD2)%12);
		this[i].sgz7 =jznyy([lD2]%10+''+[lD2]%12);

		this[i].sgz8 =jznyy([lM2]%10+''+[lM2]%12);
		this[i].sgz9 =jznyy([lY2]%10+''+[lY2]%12);
		this[i].sgz3 =cyclical6(lM2%12,(lD2)%12);
		this[i].dayglu ='◇是日命禄：<font color=red>'+dayglk[(lD2)%10]+'命互禄 '+dayglus+'</font>';
		this[i].ssfw ='◇喜神：<font color=red>'+xs+'</font> ◇福神：<font color=red>'+fs+'</font> ◇财神：<font color=red>'+cs;
		this[i].fs1 ='本日物候：'+xs1
		if((i+this.firstWeek)%7==0)   this[i].color = 'red'
		if((i+this.firstWeek)%14==13) this[i].color = 'red'
		this[i].sgzzm2 = '◆'+sTerm(y,m*2  )+'日'+ solarTerm[m*2]+'  '+'◆'+sTerm(y,m*2+1)+'日'+ solarTerm[m*2+1]
	}
	if(y==tY && m==tM) this[tD-1].solarTerms +='今天'

	tmp1=sTerm(y,m*2  )-1
	tmp2=sTerm(y,m*2+1)-1
	this[tmp1].solarTerms = solarTerm[m*2]
	this[tmp2].solarTerms = solarTerm[m*2+1]

	if(m==3) this[tmp1].color = '#CC4AF7'
	for(i in sFtv)
		if(sFtv[i].match(/^(\d{2})(\d{2})([\s\*])(.+)$/))
			if(Number(RegExp.$1)==(m+1)) {
				this[Number(RegExp.$2)-1].solarFestival += RegExp.$4 + ' '
				if(RegExp.$3=='*') this[Number(RegExp.$2)-1].color = '#BC02D7'
			}
	for(i in wFtv)
		if(wFtv[i].match(/^(\d{2})(\d)(\d)([\s\*])(.+)$/))
			if(Number(RegExp.$1)==(m+1)) {
				tmp1=Number(RegExp.$2);
				tmp2=Number(RegExp.$3);
				if(tmp1<5)
					this[((this.firstWeek>tmp2)?7:0) + 7*(tmp1-1) + tmp2 - this.firstWeek].solarFestival += RegExp.$5 + ' ';
				else {
					tmp1 -= 5;
					tmp3 = (this.firstWeek+this.length-1)%7;
					this[this.length - tmp3 - 7*tmp1 + tmp2 - (tmp2>tmp3?7:0) - 1 ].solarFestival += RegExp.$5 + ' ';
				}
			}

	for(i in lFtv)
		if(lFtv[i].match(/^(\d{2})(.{2})([\s\*])(.+)$/)) {
			tmp1=Number(RegExp.$1)-firstLM
			if(tmp1==-11) tmp1=1
			if(tmp1 >=0 && tmp1<n) {
				tmp2 = lDPOS[tmp1] + Number(RegExp.$2) -1
				if( tmp2 >= 0 && tmp2<this.length) {
					this[tmp2].lunarFestival += RegExp.$4 + ' '
					if(RegExp.$3=='*') this[tmp2].color = '#FF00FF'
				}
			}
		}
	if(m==2 || m==3) {
		var estDay = new easter(y);
		if(m == estDay.m)
		this[estDay.d-1].solarFestival = this[estDay.d-1].solarFestival+'复活节';
	}
	if((this.firstWeek+12)%7==5) this[12].solarFestival += '黑色星期五'
	if(y==tY && m==tM) this[tD-1].isToday = true;
}

function CalConv(d,dd) {
	return '岁煞'+sfw[dd]+' '+AnimalIdx[dd]+'日冲('+Gan5[d]+Zhi3[dd]+')'+AnimalIdx2[dd];
}

function jzny(d){
	var ny;
	if(d=='00' || d=='11') ny='海中金';
	if(d=='22' || d=='33') ny='炉中火';
	if(d=='44' || d=='55') ny='大林木';
	if(d=='66' || d=='77') ny='路旁土';
	if(d=='88' || d=='99') ny='剑锋金';
	if(d=='010' || d=='111') ny='山头火';
	if(d=='20' || d=='31') ny='洞下水';
	if(d=='42' || d=='53') ny='城墙土';
	if(d=='64' || d=='75') ny='白腊金';
	if(d=='86' || d=='97') ny='杨柳木';
	if(d=='08' || d=='19') ny='泉中水';
	if(d=='210' || d=='311') ny='屋上土';
	if(d=='40' || d=='51') ny='霹雷火';
	if(d=='62' || d=='73') ny='松柏木';
	if(d=='84' || d=='95') ny='常流水';
	if(d=='06' || d=='17') ny='沙中金';
	if(d=='28' || d=='39') ny='山下火';
	if(d=='410' || d=='511') ny='平地木';
	if(d=='60' || d=='71') ny='壁上土';
	if(d=='82' || d=='93') ny='金箔金';
	if(d=='04' || d=='15') ny='佛灯火';
	if(d=='26' || d=='37') ny='天河水';
	if(d=='48' || d=='59') ny='大驿土';
	if(d=='610' || d=='711') ny='钗钏金';
	if(d=='80' || d=='91') ny='桑柘木';
	if(d=='02' || d=='13') ny='大溪水';
	if(d=='24' || d=='35') ny='沙中土';
	if(d=='46' || d=='57') ny='天上火';
	if(d=='68' || d=='79') ny='石榴木';
	if(d=='810' || d=='911') ny='大海水';
	return(ny);
}

function jznyy(d){
	var nyy;
	if(d=='00' || d=='11') nyy='金';
	if(d=='22' || d=='33') nyy='火';
	if(d=='44' || d=='55') nyy='木';
	if(d=='66' || d=='77') nyy='土';
	if(d=='88' || d=='99') nyy='金';
	if(d=='010' || d=='111') nyy='火';
	if(d=='20' || d=='31') nyy='水';
	if(d=='42' || d=='53') nyy='土';
	if(d=='64' || d=='75') nyy='金';
	if(d=='86' || d=='97') nyy='木';
	if(d=='08' || d=='19') nyy='水';
	if(d=='210' || d=='311') nyy='土';
	if(d=='40' || d=='51') nyy='火';
	if(d=='62' || d=='73') nyy='木';
	if(d=='84' || d=='95') nyy='水';
	if(d=='06' || d=='17') nyy='金';
	if(d=='28' || d=='39') nyy='火';
	if(d=='410' || d=='511') nyy='木';
	if(d=='60' || d=='71') nyy='土';
	if(d=='82' || d=='93') nyy='金';
	if(d=='04' || d=='15') nyy='火';
	if(d=='26' || d=='37') nyy='水';
	if(d=='48' || d=='59') nyy='土';
	if(d=='610' || d=='711') nyy='金';
	if(d=='80' || d=='91') nyy='木';
	if(d=='02' || d=='13') nyy='水';
	if(d=='24' || d=='35') nyy='土';
	if(d=='46' || d=='57') nyy='火';
	if(d=='68' || d=='79') nyy='木';
	if(d=='810' || d=='911') nyy='水';
	return(nyy);
}

function easter(y) {
	var term2=sTerm(y,5);
	var dayTerm2 = new Date(Date.UTC(y,2,term2,0,0,0,0));
	var lDayTerm2 = new Lunar(dayTerm2);

	if(lDayTerm2.day<15)
		var lMlen= 15-lDayTerm2.day;
	else
		var lMlen= (lDayTerm2.isLeap? leapDays(y): monthDays(y,lDayTerm2.month)) - lDayTerm2.day + 15;
	var l15 = new Date(dayTerm2.getTime() + 86400000*lMlen );
	var dayEaster = new Date(l15.getTime() + 86400000*( 7-l15.getUTCDay() ) );
	this.m = dayEaster.getUTCMonth();
	this.d = dayEaster.getUTCDate();
}

function cDay(d){
	var s;

	switch (d) {
		case 10:
			s = '初十'; break;
		case 20:
			s = '二十'; break;
		case 30:
			s = '三十'; break;
		default :
			s = nStr2[Math.floor(d/10)];
			s += nStr1[d%10];
	}
	return(s);
}

function cDay2(d){
	var s2;

	switch (d) {
		case 1:
			s2 = '正'; break;
		case 2:
			s2 = '二'; break;
		case 3:
			s2 = '三'; break;
		case 4:
			s2 = '四'; break;
		case 5:
			s2 = '五'; break;
		case 6:
			s2 = '六'; break;
		case 7:
			s2 = '七'; break;
		case 8:
			s2 = '八'; break;
		case 9:
			s2 = '九'; break;
		case 10:
			s2 = '十'; break;
		case 11:
			s2 = '十一'; break;
		case 12:
			s2 = '十二'; break;
		default :
	}
	return(s2);
}

var cld;

function drawCld(SY,SM, siteUrl) {
	var i,sD,s,size,bsms,rmms,SY2;
	cld = new calendar(SY,SM);

	if(SY>1874 && SY<1909) yDisplay = '光绪&nbsp;' + (((SY-1874)==1)?'元':SY-1874)
	if(SY>1908 && SY<1912) yDisplay = '宣统&nbsp;' + (((SY-1908)==1)?'元':SY-1908)
	if(SY>1911 && SY<1949) yDisplay = '民国&nbsp;' + (((SY-1911)==1)?'元':SY-1911)
	if(SY>1948 && SY<1950) yDisplay = '中华人民共和国' + (((SY-1948)==1)?'成立':SY-1948)
	if(SY>1949) yDisplay = '中华人民共和国成立' + (((SY-1949)==1)?'元':SY-1949)
	if(SM==0){SY2=SY-1;}else{SY2=SY;}
	GZ.innerHTML ='&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;<a onmouseout="hidetip2()" onmouseover=showtip2(this,event,"'+'公元'+SY+'年'+(SM+1)+'月","0","'+SM+'") href="#">'+ yDisplay+'年'+'</a> <font color=#FFCC00>●</font>&nbsp;'+Animals[(SY2-4)%12]+'年[农历' + cyclical(SY2-1900+36)+'年]' +'</a>&nbsp;<img src="'+siteUrl+'HuangLi/' + ((SY2-4)%12+1) +'.gif">&nbsp;' ;
	YMBG.innerHTML = monthName[SM]+"&nbsp;" +SY+"</a>"  ;
	for(i=0;i<42;i++) {
		sObj=eval('SD'+ i);
		lObj=eval('LD'+ i);
		sObj.parentElement.parentElement.background = '';
		sD = i - cld.firstWeek;
		if(sD>-1 && sD<cld.length) {
			sObj.innerHTML = sD+1;
			sObj.style.color = cld[sD].color;
			if(cld[sD].lDay==1) {
				rmms=(cld[sD].isLeap?'闰':'');
				if(rmms!=''){
					bsms='';
				} else {
					bsms=(monthDays(cld[sD].lYear,cld[sD].lMonth)==29?'小':'大');
				}
				lObj.innerHTML = '<b>'+rmms + cld[sD].lMonth + '月' + bsms+'</b>';
			} else {
				lObj.innerHTML = cDay(cld[sD].lDay);
			}
			s=cld[sD].lunarFestival;
			if(s.length>0) {
				if(s.length>4) s = s.substr(0, 3)+'…';
				s = s.fontcolor('#C49402');
			} else {
				s=cld[sD].solarFestival;
				if(s.length>0) {
					size = (s.charCodeAt(0)>0 && s.charCodeAt(0)<128)?8:4;
					if(s.length>size+1) s = s.substr(0, size-1)+'…';
					s = s.fontcolor('#FF8000');
				} else {
					s=cld[sD].solarTerms;
					if(s.length>0) s = s.fontcolor('#309F00');
				}
			}
			if(s.length>0) lObj.innerHTML = s;
		} else {
			sObj.innerHTML = '';
			lObj.innerHTML = '';
		}
	}
}

function changeCld(siteUrl) {
	var y,m;
	y=CLD.SY.selectedIndex+1900;
	m=CLD.SM.selectedIndex;
	drawCld(y,m,siteUrl);
}

function pushBtm(K) {
	switch (K){
		case 'YU' :
			if(CLD.SY.selectedIndex>0) CLD.SY.selectedIndex--;
			break;
		case 'YD' :
			if(CLD.SY.selectedIndex<149) CLD.SY.selectedIndex++;
			break;
		case 'MU' :
			if(CLD.SM.selectedIndex>0) {
				CLD.SM.selectedIndex--;
			} else {
				CLD.SM.selectedIndex=11;
				if(CLD.SY.selectedIndex>0) CLD.SY.selectedIndex--;
			}
			break;
		case 'MD' :
			if(CLD.SM.selectedIndex<11) {
				CLD.SM.selectedIndex++;
			} else {
				CLD.SM.selectedIndex=0;
				if(CLD.SY.selectedIndex<200) CLD.SY.selectedIndex++;
			}
			break;
		default :
			CLD.SY.selectedIndex=tY-1900;
			CLD.SM.selectedIndex=tM;
	}
	changeCld();
}

var Today = new Date();
var tY = Today.getFullYear();
var tM = Today.getMonth();
var tD = Today.getDate();
var tD1 = tD
var ybm1 = tM+1;
var azz = new Date()
azz = azz.valueOf()
var tD2 = new Date((azz + 1 * 24 * 60 * 60 * 1000)).getDate();
var ybm2 = new Date((azz + 1 * 24 * 60 * 60 * 1000)).getMonth()+1;
var tD3 = new Date((azz + 2 * 24 * 60 * 60 * 1000)).getDate();
var ybm3 = new Date((azz + 3 * 24 * 60 * 60 * 1000)).getMonth()+1;
var d2r = Math.PI / 180.0;
var width = "130";
var offsetx = 2;
var offsety = 16;
var snow = 0;
var snow2 = 0;

function Ymd2Jd(yy,mm,dd) {
	var days,tmp,yym1;
	yym1 = yy - 1;
	days = 1721422;
	solarMonth[1] = 28;
	if (yy % 4 == 0) {
		solarMonth[1] = 29;
		if (yy > 1582) {
			if (yy % 100 == 0) {
				solarMonth[1] = 28;
				if (yy %400 == 0) {
					solarMonth[1] = 29;
				}
			}
		}
	}
	days += Math.floor(365.25 * yym1 + 0.1);
	for (m = 0; m < (mm - 1) ; m++) {
		days += solarMonth[m];
	}
	days += dd;
	if (days >= 2299160) days -= 10;
	if (yym1 >= 1600) {
		days -= Math.floor((yym1 - 1600 + 0.1) / 100);
		days += Math.floor((yym1 - 1600 + 0.1) / 400);
	}
	return days;
}

function GetSukuD(dd) {
	var s;
	s = (dd + SukuDofs) % 28;
	return Sukuyou[s];
}

function GetSuku2D(dd) {
	var s;
	s = (dd + SukuDofs) % 28;
	return Sukuyou2[s];
}

function GetSuku3D(dd) {
	var s;
	s = (dd + SukuDofs) % 28;
	return Sukuyou3[s];
}

function GetSuku4D(dd) {
	var s;
	s = (dd + SukuDofs) % 28;
	return Sukuyou4[s];
}

function Get6you(omm,odd) {
	var k;
	k = (omm + odd + 4) % 6;
	return Rokuyou[k];
}

function mOvr(v, siteUrl) {
	var s,festival,bt,imgsr,rqcolor,jcrnmu,jy,rmm,bsm;
	var sObj=eval('SD'+ v);
	var d=sObj.innerHTML-1;
	if (snow == 0) {
		d = tD-1;
		snow = 1;
	}
	if(sObj.innerHTML!='') {
		sObj.style.cursor = 'help';
		if(cld[d].solarTerms!='' && cld[d].isToday == true) {
			bt='tablebody2';
			if(cld[d].solarTerms!='今天'){imgsr='今天：';}else{imgsr='';}
		} else {
			bt='tablebody1';
			imgsr='是日：'
		}
		rcrltd.className=bt;
		if(cld[d].solarTerms == '' && cld[d].solarFestival == '' && cld[d].lunarFestival == '') {
			festival = cld[d].sgzzm2;
		} else {
			festival = imgsr + cld[d].solarTerms + ' '+ cld[d].solarFestival + ' ' + cld[d].lunarFestival;
		}
		if(cld[d].sgz5!=0){jy=cld[d].sgz5;}else{jy=jcr(cld[d].sgz3);}
		rmm=(cld[d].isLeap?'闰':'');
		if(rmm!=''){bsm='';}else{bsm=(monthDays(cld[d].lYear,cld[d].lMonth)==29?'小':'大');}
		if(cld[d].week=='日'){rqcolor='<font color=#FF8040>'}else{rqcolor=''}
		s= '<table border="0" cellpadding="1" cellspacing="1" class=tableborder1 style="table-layout: fixed;height:305;width:100%">' +
			'<tr height=25><th>'+ festival +'</th></tr>' +
			'<TR><td class='+bt+'>' +
				'<TABLE WIDTH=100% BORDER=0 CELLPADDING=0 CELLSPACING=0>' +
				'<TR><TD ALIGN="center">' +
					'<FONT COLOR="#004000" face="Arial"><b><span style="letter-spacing: 5px;font-size:11pt;">'+rqcolor+cld[d].sYear+'年'+cld[d].sMonth+'月</span></font>' +
					'<br>' +
					'<font style="font-size:19pt;line-height:24px">'+cld[d].sDay+'</font>' +
					'<br>' +
					'<font STYLE="font-size:14px;line-height:28px">星期'+cld[d].week+'</b></font>' +
					'<br>' +
					'<font color="#800080">农历'+cld[d].cYear+cld[d].sgz9+'年 '+rmm+cDay2(cld[d].lMonth)+'月'+bsm+'  '+cDay(cld[d].lDay)+'日</font>' +
					'<br>' +
					'<font color="#800080">'+cld[d].cMonth+cld[d].sgz8+'(</font>' +
					'<font color=#008000>'+moonglk[cld[d].lMonth-1]+'</font>' +
					'<font color="#800080">)月 '+cld[d].cDay+cld[d].sgz7+GetSuku4D(Ymd2Jd(cld[d].sYear,cld[d].sMonth,cld[d].sDay))+cld[d].sgz3+'日</font>' +
					'<br>' +
					'<font color=#008000 style="line-height: 25px">'+cld[d].fs1+ '</font>' +
					'<br>' +
					'<FONT color=#0000A0>'+cld[d].sgz4 +'</font>' +
					'<br>' +
					'<a style="cursor: help" onmouseout="hidetip2()" onmouseover=showtip2(this,event,"'+Jd2KyuuseiNameL(Ymd2Jd(cld[d].sYear,cld[d].sMonth,cld[d].sDay))+'","'+Ymd2Jd(cld[d].sYear,cld[d].sMonth,cld[d].sDay)+'","宿名：'+GetSuku2D(Ymd2Jd(cld[d].sYear,cld[d].sMonth,cld[d].sDay))+'('+GetSukuD(Ymd2Jd(cld[d].sYear,cld[d].sMonth,cld[d].sDay))+')")>' + Jd2KyuuseiNameL(Ymd2Jd(cld[d].sYear,cld[d].sMonth,cld[d].sDay))+'<br>宿名：'+GetSukuD(Ymd2Jd(cld[d].sYear,cld[d].sMonth,cld[d].sDay))+GetSuku2D(Ymd2Jd(cld[d].sYear,cld[d].sMonth,cld[d].sDay))+' 六曜：'+Get6you(cld[d].sMonth,cld[d].sDay) +
					'<br>' +
					'值日：'+cld[d].sgz6+' 五行：'+cld[d].sgz2 +
					'<br>' +
					cld[d].pgday +
					'</a>' +
				'</TD></TR></TABLE>' +
			'</TD></TR>' +
			'<TR><TD height=38 class='+bt+' align=center>' +
				'<table border="0" cellpadding="4" cellspacing="0"><tr>' +
				'<td><font color="#800080">'+cld[d].cDay+'<br>'+cld[d].sgz3+'日</td>' +
				'<td>'+jy+'</TD>' +
				'</TR></TABLE>' +
			'</TD></TR></TABLE>';
	var mnname;
		var moonimg;
		if (cld[d].lDay >= 24) mnname = '有明月';
		if (cld[d].lDay <= 14) mnname = '宵月';
		if (cld[d].lDay <= 7) mnname = '夕月';
		if (cld[d].lDay == 1) mnname = '新(朔)月';
		if (cld[d].lDay == 2) mnname = '既朔月';
		if (cld[d].lDay == 3) mnname = '娥眉新月';
		if (cld[d].lDay == 4) mnname = '娥眉新月';
		if (cld[d].lDay == 5) mnname = '娥眉月';
		if (cld[d].lDay == 7) mnname = '上弦月';
		if (cld[d].lDay == 8) mnname = '上弦月';
		if (cld[d].lDay == 9) mnname = '九夜月';
		if (cld[d].lDay == 13) mnname = '渐盈凸月';
		if (cld[d].lDay == 14) mnname = '小望月';
		if (cld[d].lDay == 15) mnname = '满(望)月';
		if (cld[d].lDay == 16) mnname = '既望月';
		if (cld[d].lDay == 17) mnname = '立待月';
		if (cld[d].lDay == 18) mnname = '居待月';
		if (cld[d].lDay == 19) mnname = '寝待月';
		if (cld[d].lDay == 20) mnname = '更待月';
		if (cld[d].lDay == 21) mnname = '渐亏凸月';
		if (cld[d].lDay == 22) mnname = '下弦月';
		if (cld[d].lDay == 23) mnname = '下弦月';
		if (cld[d].lDay == 26) mnname = '娥眉残月';
		if (cld[d].lDay == 27) mnname = '娥眉残月';
		if (cld[d].lDay == 28) mnname = '残月';
		if (cld[d].lDay == 29) mnname = '晓月';
		if (cld[d].lDay == 30) mnname = '晦月';
		moonimg='<IMG SRC="'+siteUrl+'HuangLi/moon' + cld[d].lDay + '.gif" WIDTH=55 HEIGHT=55>'
		sg.innerHTML ='<b>月相：'+mnname+'</b>';
		sg2.innerHTML =s;
		sg3.innerHTML =cld[d].dGz;
		sg4.innerHTML =cld[d].sgz;
		sg5.innerHTML =moonimg;
		dayglu1.innerHTML =cld[d].dayglu+' &nbsp;'+cld[d].ssfw;
	}
}

function Main(v,T) {
	var OutString = "";
	var quady = new Array;
	var sunp = new Array;
	var moonp = new Array;
	var y, m, day, glong, glat, tz, numday, mj, lst1, i,jwnum,jing,jings,wei,weis,XZ,hb,yb1,yb2;
	var rads = 0.0174532925, sinmoonalt;
	var sObj=eval('SD'+ v);
	var d=sObj.innerHTML-1;
	if(sObj.innerHTML!='') {
		y=cld[d].sYear;
		m=cld[d].sMonth;
		jwnum=CLD.per_des;
		if(jwnum==undefined){
			jing='113';
			jings='07';
			wei='23';
			weis='02';
			hb=0;
			yb1=37;
		} else {
			XZ=getString(jwnum,5);
			hb=getString(jwnum,10);
			if(XZ==0) XZ='';
			jing=XZ+''+getString(jwnum,4)+''+getString(jwnum,3);
			jings=getString(jwnum,2)+''+getString(jwnum,1);
			wei=getString(jwnum,9)+''+getString(jwnum,8);
			weis=getString(jwnum,7)+''+getString(jwnum,6);
			yb1=(getString(jwnum,12)+''+getString(jwnum,11))*1;
		}
		if(T==7){
			today = new Date () ;
			day =today.getDate();
		} else {
			day =cld[d].sDay;
		}
		glong = parseInt(jing)+parseFloat(jings)/60;
		glat = parseInt(wei)+parseFloat(weis)/60;
		tz = parseFloat(8);
		mj = mjd(day, m, y, 0.0);
		rcrl.innerHTML ='<a style="cursor: help" onmouseout="hidetip2()" onmouseover=showtip2(this,event,"","2","'+yb1+'")>'+
		Cal(mj, tz, glong, glat,hb)+'<br><span style="line-height: 20px; color:#804000">◇东经：'+jing+'.'+jings+'度 ◇北纬：'+wei+'.'+weis+'度 <br></a>';
	}
}

function hrsmin(hours) {
	var hrs, h, m, dum;
	hrs = Math.floor(hours * 60 + 0.5)/ 60.0;
	h = Math.floor(hrs);
	m = Math.floor(60 * (hrs - h) + 0.5);
	if(h<10)h="0"+h;
	if(m<10)m="0"+m;
	dum = h +"时"+ m+"分";
	if (dum < 1000) dum = "0" + dum;
	if (dum <100) dum = "0" + dum;
	if (dum < 10) dum = "0" + dum;
	return dum;
}

function ipart(x) {
	var a;
	if (x> 0) {
		a = Math.floor(x);
	} else {
		a = Math.ceil(x);
	}
	return a;
}

function frac(x) {
	var a;
	a = x - Math.floor(x);
	if (a < 0) a += 1;
	return a;
}

function round(num, dp) {
	return Math.round (num * Math.pow(10, dp)) / Math.pow(10, dp);
}

function range(x) {
	var a, b;
	b = x / 360;
	a = 360 * (b - ipart(b));
	if (a  < 0 ) {
		a = a + 360
	}
	return a
}

function mjd(day, month, year, hour) {
	var a, b;
	if (month <= 2) {
		month = month + 12;
		year = year - 1;
	}
	a = 10000.0 * year + 100.0 * month + day;
	if (a <= 15821004.1) {
		b = -2 * Math.floor((year + 4716)/4) - 1179;
	} else {
		b = Math.floor(year/400) - Math.floor(year/100) + Math.floor(year/4);
	}
	a = 365.0 * year - 679004.0;
	return (a + b + Math.floor(30.6001 * (month + 1)) + day + hour/24.0);
}

function quad(ym, yz, yp) {
	var nz, a, b, c, dis, dx, xe, ye, z1, z2, nz;
	var quadout = new Array;

	nz = 0;
	a = 0.5 * (ym + yp) - yz;
	b = 0.5 * (yp - ym);
	c = yz;
	xe = -b / (2 * a);
	ye = (a * xe + b) * xe + c;
	dis = b * b - 4.0 * a * c;
	if (dis > 0)	{
		dx = 0.5 * Math.sqrt(dis) / Math.abs(a);
		z1 = xe - dx;
		z2 = xe + dx;
		if (Math.abs(z1) <= 1.0) nz += 1;
		if (Math.abs(z2) <= 1.0) nz += 1;
		if (z1 < -1.0) z1 = z2;
	}
	quadout[0] = nz;
	quadout[1] = z1;
	quadout[2] = z2;
	quadout[3] = xe;
	quadout[4] = ye;
	return quadout;
}


function lmst(mjd, glong) {
	var lst, t, d;
	d = mjd - 51544.5
	t = d / 36525.0;
	lst = range(280.46061837 + 360.98564736629 * d + 0.000387933 *t*t - t*t*t / 38710000);
	return (lst/15.0 + glong/15);
}


function minisun(t) {
	var p2 = 6.283185307, coseps = 0.91748, sineps = 0.39778;
	var L, M, DL, SL, X, Y, Z, RHO, ra, dec;
	var suneq = new Array;

	M = p2 * frac(0.993133 + 99.997361 * t);
	DL = 6893.0 * Math.sin(M) + 72.0 * Math.sin(2 * M);
	L = p2 * frac(0.7859453 + M / p2 + (6191.2 * t + DL)/1296000);
	SL = Math.sin(L);
	X = Math.cos(L);
	Y = coseps * SL;
	Z = sineps * SL;
	RHO = Math.sqrt(1 - Z * Z);
	dec = (360.0 / p2) * Math.atan(Z / RHO);
	ra = (48.0 / p2) * Math.atan(Y / (X + RHO));
	if (ra <0 ) ra += 24;
	suneq[1] = dec;
	suneq[2] = ra;
	return suneq;
}

function sin_alt(iobj, mjd0, hour, glong, cglat, sglat) {
	var mjd, t, ra, dec, tau, salt, rads = 0.0174532925;
	var objpos = new Array;
	mjd = mjd0 + hour/24.0;
	t = (mjd - 51544.5) / 36525.0;
	if (iobj == 1) {
		objpos = minimoon(t);
	} else {
		objpos = minisun(t);
	}
	ra = objpos[2];
	dec = objpos[1];
	tau = 15.0 * (lmst(mjd, glong) - ra);
	salt = sglat * Math.sin(rads*dec) + cglat * Math.cos(rads*dec) * Math.cos(rads*tau);
	return salt;
}

function getzttime(mjd, tz, glong) {
	var sglong, sglat, date, ym, yz, utrise, utset, j;
	var yp, nz, hour, z1, z2, iobj, rads = 0.0174532925;
	var quadout = new Array;

	sinho = Math.sin(rads * -0.833);
	date = mjd - tz/24;
	hour = 1.0;
	ym = sin_alt(2, date, hour - 1.0, glong, 1, 0) - sinho;

	while(hour < 25) {
		yz = sin_alt(2, date, hour, glong, 1, 0) - sinho;
		yp = sin_alt(2, date, hour + 1.0, glong, 1, 0) - sinho;
		quadout = quad(ym, yz, yp);
		nz = quadout[0];
		z1 = quadout[1];
		z2 = quadout[2];
		xe = quadout[3];
		ye = quadout[4];

		if (nz == 1) {
			if (ym < 0.0)
				utrise = hour + z1;
			else
				utset = hour + z1;

		}
		if (nz == 2) {
			if (ye < 0.0) {
				utrise = hour + z2;
				utset = hour + z1;
			}
			else {
				utrise = hour + z1;
				utset = hour + z2;
			}
		}
		ym = yp;
		hour += 2.0;
	}
	var zt=(utrise+utset)/2;
	if(zt<utrise)
		zt=(zt+12)%24;
	return zt;
}

function Cal(mjd, tz, glong, glat,hb) {

	var sglong, sglat, date, ym, yz, above, utrise, utset,hbt, j;
	var yp, nz, rise, sett, hour, z1, z2, iobj, rads = 0.0174532925;
	var quadout = new Array;
	var always_up = "无日落";
	var always_down = "无日出";
	var outstring = "";
	hbt=hb*0.083;
	sinho = Math.sin(rads * -0.833);
	sglat = Math.sin(rads * glat);
	cglat = Math.cos(rads * glat);
	date = mjd - tz/24;
	rise = false;
	sett = false;
	above = false;
	hour = 1.0;
	ym = sin_alt(2, date, hour - 1.0, glong, cglat, sglat) - sinho;
	if (ym > 0.0) above = true;
	while(hour < 25 && (sett == false || rise == false)) {
		yz = sin_alt(2, date, hour, glong, cglat, sglat) - sinho;
		yp = sin_alt(2, date, hour + 1.0, glong, cglat, sglat) - sinho;
		quadout = quad(ym, yz, yp);
		nz = quadout[0];
		z1 = quadout[1];
		z2 = quadout[2];
		xe = quadout[3];
		ye = quadout[4];
		if (nz == 1) {
			if (ym < 0.0) {
				utrise = hour + z1-hbt;
				rise = true;
			}
			else {
				utset = hour + z1+hbt;
				sett = true;
			}
		}
		if (nz == 2) {
			if (ye < 0.0) {
				utrise = hour + z2-hbt;
				utset = hour + z1+hbt;
			}
			else {
				utrise = hour + z1-hbt;
				utset = hour + z2+hbt;
			}
		}
		ym = yp;
		hour += 2.0;
	}
	if (rise == true || sett == true ) {
		if (rise == true) outstring +='◎天亮：'+ hrsmin(utrise-0.5)+' ◎天黑：'+hrsmin(utset+0.5)+'<br>◎日出：' + hrsmin(utrise);
		else outstring += "◎日出：无日出"+"  ";
		if (sett == true) outstring += " ◎日落：" + hrsmin(utset)+"<br>";
		else outstring += " ◎日落：无日落<br>";
		var zt=getzttime(mjd, tz, glong)
		outstring+= " ◎日中：" + hrsmin(zt);
		outstring += " ◎昼长："+hrsmin(utset-utrise);
	}
	else {
		if (above == true){
		 outstring += always_up;
		 var zt=getzttime(mjd, tz, glong);
		 outstring+="<br>"+"日中："+hrsmin(zt);
		 outstring += "<br>昼长："+hrsmin(24);
		}
		else outstring += always_down;
	}
	return outstring;
}

function tick() {
	var today,z1,z2,z3,z4,z5,z6,z7,z8,z9,z10,z11,z12,ztx,scolor;
	var sccolor=new Array("red","#00FF00");
	today = new Date()
	var hours = today.getHours();
	var minutes = today.getMinutes();
	var seconds = today.getSeconds();
	var stM =tM+1
	if(hours<10) hours='0'+hours
	if(minutes<10) minutes='0'+minutes
	if(seconds<10) seconds='0'+seconds
	scolor=sccolor[seconds%2]
	Clock.innerHTML =tY+'年'+stM+'月'+tD+'日&nbsp;'+hours+':'+minutes+':'+seconds;
	if (( hours >= 1 ) && (hours < 3 )){
		z1=scolor;
		ztx="丑";
	} else {
		z1="#c0c0c0";
	}
	if (( hours >= 3 ) && (hours < 5 )) {
		z2=scolor;
		ztx="寅";
	} else {
		z2="#c0c0c0";
	}
	if (( hours >= 5 ) && (hours < 7 )) {
		z3=scolor;
		ztx="卯";
	} else {
		z3="#c0c0c0";
	}
	if (( hours >= 7 ) && (hours < 9 )){
		z4=scolor;
		ztx="辰";
	} else {
		z4="#c0c0c0";
	}
	if (( hours >= 9 ) && (hours < 11)) {
		z5=scolor;
		ztx="巳";
	} else {
		z5="#c0c0c0";
	}
	if (( hours >= 11) && (hours < 13)) {
		z6=scolor;
		ztx="午";
	} else {
		z6="#c0c0c0";
	}
	if (( hours >= 13) && (hours < 15)){
		z7=scolor;
		ztx="未";
	} else {
		z7="#c0c0c0";
	}
	if (( hours >= 15) && (hours < 17)){
		z8=scolor;
		ztx="申";
	} else {
		z8="#c0c0c0";
	}
	if (( hours >= 17) && (hours < 19)){
		z9=scolor;
		ztx="酉";
	} else {
		z9="#c0c0c0";
	}
	if (( hours >= 19) && (hours < 21)){
		z10=scolor;
		ztx="戌";
	} else {
		z10="#c0c0c0";
	}
	if (( hours >= 21) && (hours < 23)){
		z11=scolor;
		ztx="亥";
	} else {
		z11="#c0c0c0";
	}
	if (((hours >= 23) || (hours < 1))) {
		z12=scolor;
		ztx="子";
	} else {
		z12="#c0c0c0";
	}
	tim.innerHTML='<table border="0" cellpadding="0" cellspacing="0" width="100%">'+'<tr><td width=48></td><td height=2 width=20 bgcolor='+z12+'></td>'+'<td width=20 bgcolor='+z1+'></td><td width=21 bgcolor='+z2+'></td><td width=21 bgcolor='+z3+'></td>'+'<td width=20 bgcolor='+z4+'></td><td width=20 bgcolor='+z5+'></td>'+'<td width=20 bgcolor='+z6+'></td><td width=20 bgcolor='+z7+'></td>'+'<td width=20 bgcolor='+z8+'></td><td width=20 bgcolor='+z9+'></td>'+'<td width=20 bgcolor='+z10+'></td><td width=20 bgcolor='+z11+'></td>'+'<td width=22></td></tr></td></tr></table>';
	tim2.innerHTML=ztx;
	window.setTimeout("tick()", 1000);
}

function initial(siteUrl, tY) {

   CLD.SY.selectedIndex=tY-1900;
   CLD.SM.selectedIndex=tM;
   drawCld(tY,tM, siteUrl);
   tick();
}

function getString(isString,n){
       var isResult=isString.substr(isString.length-n,1);
      return  isResult;
}

var provinceOp=null;
var cityOp=null;
var PROVNUM=32;
provinceOp = new Array(PROVNUM);
provinceOp[0]=new Option("北京");
provinceOp[1]=new Option("天津");
provinceOp[2]=new Option("河北");
provinceOp[3]=new Option("山西");
provinceOp[4]=new Option("内蒙");
provinceOp[5]=new Option("辽宁");
provinceOp[6]=new Option("吉林");
provinceOp[7]=new Option("黑龙江");
provinceOp[8]=new Option("上海");
provinceOp[9]=new Option("江苏");
provinceOp[10]=new Option("浙江");
provinceOp[11]=new Option("安徽");
provinceOp[12]=new Option("福建");
provinceOp[13]=new Option("江西");
provinceOp[14]=new Option("山东");
provinceOp[15]=new Option("河南");
provinceOp[16]=new Option("湖北");
provinceOp[17]=new Option("湖南");
provinceOp[18]=new Option("广东");
provinceOp[19]=new Option("广西");
provinceOp[20]=new Option("海南");
provinceOp[21]=new Option("重庆");
provinceOp[22]=new Option("四川");
provinceOp[23]=new Option("贵州");
provinceOp[24]=new Option("云南");
provinceOp[25]=new Option("西藏");
provinceOp[26]=new Option("陕西");
provinceOp[27]=new Option("甘肃");
provinceOp[28]=new Option("青海");
provinceOp[29]=new Option("宁夏");
provinceOp[30]=new Option("新疆");
provinceOp[31]=new Option("港澳台");
cityOp = new Array(PROVNUM);
cityOp[0] = new Array(11);
cityOp[0][0]=new Option("----","00399211646");
cityOp[0][1]=new Option("北 京","00399211646");
cityOp[0][2]=new Option("平 谷","00401311710");
cityOp[0][3]=new Option("密 云","00403711685");
cityOp[0][4]=new Option("顺 义","00401311665");
cityOp[0][5]=new Option("通 县","00399211667");
cityOp[0][6]=new Option("怀 柔","00403211662");
cityOp[0][7]=new Option("大 兴","00397311633");
cityOp[0][8]=new Option("房 山","00397211598");
cityOp[0][9]=new Option("延 庆","00404711597");
cityOp[0][10]=new Option("昌 平","00402211620");
cityOp[1] = new Array(7);
cityOp[1][0]=new Option("----","011391311720");
cityOp[1][1]=new Option("天 津","011391311720");
cityOp[1][2]=new Option("宁 河","011393311783");
cityOp[1][3]=new Option("静 海","011389311692");
cityOp[1][4]=new Option("蓟 县","011400511740");
cityOp[1][5]=new Option("宝 坻","011397511730");
cityOp[1][6]=new Option("武 清","011394011705");
cityOp[2] = new Array(34);
cityOp[2][0]=new Option("----","040380211430");
cityOp[2][1]=new Option("石家庄","040380211430");
cityOp[2][2]=new Option("安国","040382411520");
cityOp[2][3]=new Option("保定","170385111530");
cityOp[2][4]=new Option("霸州","170390611624");
cityOp[2][5]=new Option("泊头","170380411634");
cityOp[2][6]=new Option("沧州","170381811652");
cityOp[2][7]=new Option("承德","100405911757");
cityOp[2][8]=new Option("定州","040383011500");
cityOp[2][9]=new Option("丰南","040393411806");
cityOp[2][10]=new Option("高碑店","040392011551");
cityOp[2][11]=new Option("蒿城","040380211450");
cityOp[2][12]=new Option("邯郸","040363611428");
cityOp[2][13]=new Option("河间","040382611605");
cityOp[2][14]=new Option("衡水","040374411542");
cityOp[2][15]=new Option("黄骅","040382111721");
cityOp[2][16]=new Option("晋州","040380211502");
cityOp[2][17]=new Option("冀州","040373411533");
cityOp[2][18]=new Option("廓坊","040393111642");
cityOp[2][19]=new Option("鹿泉","040380411419");
cityOp[2][20]=new Option("南宫","040372211523");
cityOp[2][21]=new Option("秦皇岛","090395511935");
cityOp[2][22]=new Option("任丘","040384211607");
cityOp[2][23]=new Option("三河","040395811704");
cityOp[2][24]=new Option("沙河","040365111430");
cityOp[2][25]=new Option("深州","040380111532");
cityOp[2][26]=new Option("唐山","040393611811");
cityOp[2][27]=new Option("武安","040364211411");
cityOp[2][28]=new Option("邢台","040370411430");
cityOp[2][29]=new Option("辛集","040375411512");
cityOp[2][30]=new Option("新乐","040382011441");
cityOp[2][31]=new Option("张家口","040404811453");
cityOp[2][32]=new Option("涿州","040392911559");
cityOp[2][33]=new Option("遵化","040401111758");
cityOp[3] = new Array(21);
cityOp[3][0]=new Option("----","030375411233");
cityOp[3][1]=new Option("太原","030375411233");
cityOp[3][2]=new Option("长治","030361111306");
cityOp[3][3]=new Option("大同","070400611317");
cityOp[3][4]=new Option("高平","030354811255");
cityOp[3][5]=new Option("古交","030375411209");
cityOp[3][6]=new Option("河津","030353511041");
cityOp[3][7]=new Option("侯马","030353711121");
cityOp[3][8]=new Option("霍州","030363411142");
cityOp[3][9]=new Option("介休","030370211155");
cityOp[3][10]=new Option("晋城","030353011251");
cityOp[3][11]=new Option("临汾","030360511131");
cityOp[3][12]=new Option("潞城","030362111314");
cityOp[3][13]=new Option("朔州","080391911226");
cityOp[3][14]=new Option("孝义","030370811148");
cityOp[3][15]=new Option("忻州","030382411243");
cityOp[3][16]=new Option("阳泉","030375111334");
cityOp[3][17]=new Option("永济","030345211027");
cityOp[3][18]=new Option("原平","030384311242");
cityOp[3][19]=new Option("榆次","030374111243");
cityOp[3][20]=new Option("运城","030350211059");
cityOp[4] = new Array(20);
cityOp[4][0]=new Option("----","020404811141");
cityOp[4][1]=new Option("呼和浩特","020404811141");
cityOp[4][2]=new Option("包头","160403910949");
cityOp[4][3]=new Option("赤峰","020421711858");
cityOp[4][4]=new Option("东胜","020394810959");
cityOp[4][5]=new Option("二连浩特","180433811158");
cityOp[4][6]=new Option("额尔古纳","020501312011");
cityOp[4][7]=new Option("丰镇","020402711309");
cityOp[4][8]=new Option("根河","020504812129");
cityOp[4][9]=new Option("海拉尔","020491211939");
cityOp[4][10]=new Option("霍林郭勒","020453211938");
cityOp[4][11]=new Option("集宁","020410211306");
cityOp[4][12]=new Option("临河","020404610722");
cityOp[4][13]=new Option("满洲里","020493511723");
cityOp[4][14]=new Option("通辽","020433712216");
cityOp[4][15]=new Option("乌兰浩特","020460312203");
cityOp[4][16]=new Option("乌海","020394010648");
cityOp[4][17]=new Option("锡林浩特","020435711603");
cityOp[4][18]=new Option("牙克石","020491712040");
cityOp[4][19]=new Option("扎兰屯","020480012247");
cityOp[5] = new Array(30);
cityOp[5][0]=new Option("----","610414812325");
cityOp[5][1]=new Option("沈阳","610414812325");
cityOp[5][2]=new Option("鞍山","660410712300");
cityOp[5][3]=new Option("北票","610414812047");
cityOp[5][4]=new Option("本溪","610411812346");
cityOp[5][5]=new Option("朝阳","610413412027");
cityOp[5][6]=new Option("大连","630385512136");
cityOp[5][7]=new Option("丹东","650400812422");
cityOp[5][8]=new Option("大石桥","610403712231");
cityOp[5][9]=new Option("东港","610395312408");
cityOp[5][10]=new Option("凤城","610402812402");
cityOp[5][11]=new Option("抚顺","610415112354");
cityOp[5][12]=new Option("阜新","610420112139");
cityOp[5][13]=new Option("盖州","610402412221");
cityOp[5][14]=new Option("海城","610405112243");
cityOp[5][15]=new Option("葫芦岛","610404512051");
cityOp[5][16]=new Option("锦州","670410712109");
cityOp[5][17]=new Option("开原","610423212402");
cityOp[5][18]=new Option("辽阳","610411612312");
cityOp[5][19]=new Option("凌海","610411012121");
cityOp[5][20]=new Option("凌源","610411411922");
cityOp[5][21]=new Option("盘锦","610410712203");
cityOp[5][22]=new Option("普兰店","610392312158");
cityOp[5][23]=new Option("铁法","610422812332");
cityOp[5][24]=new Option("铁岭","610421812351");
cityOp[5][25]=new Option("瓦房店","610393712200");
cityOp[5][26]=new Option("兴城","610403712041");
cityOp[5][27]=new Option("新民","610415912249");
cityOp[5][28]=new Option("营口","700403912213");
cityOp[5][29]=new Option("庄河","610394112258");
cityOp[6] = new Array(27);
cityOp[6][0]=new Option("----","620435412519");
cityOp[6][1]=new Option("长春","620435412519");
cityOp[6][2]=new Option("白城","620453812250");
cityOp[6][3]=new Option("白山","680415612626");
cityOp[6][4]=new Option("大安","620453012418");
cityOp[6][5]=new Option("德惠","620443212542");
cityOp[6][6]=new Option("敦化","620432212813");
cityOp[6][7]=new Option("公主岭","620433112449");
cityOp[6][8]=new Option("和龙","620423212900");
cityOp[6][9]=new Option("桦甸","620425812644");
cityOp[6][10]=new Option("珲春","620425213022");
cityOp[6][11]=new Option("集安","620410812611");
cityOp[6][12]=new Option("蛟河","620434212721");
cityOp[6][13]=new Option("吉林","620435212633");
cityOp[6][14]=new Option("九台","620440912551");
cityOp[6][15]=new Option("辽源","620425412509");
cityOp[6][16]=new Option("临江","620414912653");
cityOp[6][17]=new Option("龙井","620424612926");
cityOp[6][18]=new Option("梅河口","620423212540");
cityOp[6][19]=new Option("舒兰","620442412657");
cityOp[6][20]=new Option("四平","620431012422");
cityOp[6][21]=new Option("松原","620451112449");
cityOp[6][22]=new Option("洮南","620452012247");
cityOp[6][23]=new Option("通化","620414312556");
cityOp[6][24]=new Option("图们","680425712951");
cityOp[6][25]=new Option("延吉","680425412930");
cityOp[6][26]=new Option("愉树","680444912632");
cityOp[7] = new Array(30);
cityOp[7][0]=new Option("----","600454412636");
cityOp[7][1]=new Option("哈尔滨","600454412636");
cityOp[7][2]=new Option("阿城","600453212658");
cityOp[7][3]=new Option("安达","600462412518");
cityOp[7][4]=new Option("北安","600481512631");
cityOp[7][5]=new Option("大庆","600463612501");
cityOp[7][6]=new Option("富锦","600471513202");
cityOp[7][7]=new Option("海林","600443512921");
cityOp[7][8]=new Option("海伦","600472812657");
cityOp[7][9]=new Option("鹤岗","600472013016");
cityOp[7][10]=new Option("黑河","690501412729");
cityOp[7][11]=new Option("佳木斯","600464713022");
cityOp[7][12]=new Option("鸡西","600451713057");
cityOp[7][13]=new Option("密山","600453213150");
cityOp[7][14]=new Option("牡丹江","640443512936");
cityOp[7][15]=new Option("讷河","600482912451");
cityOp[7][16]=new Option("宁安","600442112928");
cityOp[7][17]=new Option("齐齐哈尔","690472012357");
cityOp[7][18]=new Option("七台河","600454813049");
cityOp[7][19]=new Option("双城","600452212615");
cityOp[7][20]=new Option("尚志","600451412755");
cityOp[7][21]=new Option("双鸭山","600463813111");
cityOp[7][22]=new Option("绥芬河","690442513111");
cityOp[7][23]=new Option("绥化","600463812659");
cityOp[7][24]=new Option("铁力","600465912801");
cityOp[7][25]=new Option("同江","600473913230");
cityOp[7][26]=new Option("五常","600445512711");
cityOp[7][27]=new Option("五大连池","600483812607");
cityOp[7][28]=new Option("伊春","600474212856");
cityOp[7][29]=new Option("肇东","600460412558");
cityOp[8] = new Array(11);
cityOp[8][0]=new Option("----","200312212148");
cityOp[8][1]=new Option("上 海","200312212148");
cityOp[8][2]=new Option("嘉 定","200314012124");
cityOp[8][3]=new Option("宝 山","200314112148");
cityOp[8][4]=new Option("川 沙","200311912170");
cityOp[8][5]=new Option("南 汇","200310512176");
cityOp[8][6]=new Option("奉 贤","200309212146");
cityOp[8][7]=new Option("松 江","200310012124");
cityOp[8][8]=new Option("金 山","200308912116");
cityOp[8][9]=new Option("青 浦","200311512110");
cityOp[8][10]=new Option("崇 明","200317312140");
cityOp[9] = new Array(40);
cityOp[9][0]=new Option("----","210320311846");
cityOp[9][1]=new Option("南京","210320311846");
cityOp[9][2]=new Option("常熟","210313912043");
cityOp[9][3]=new Option("常州","300314711958");
cityOp[9][4]=new Option("丹阳","210320011932");
cityOp[9][5]=new Option("东台","210325112019");
cityOp[9][6]=new Option("高邮","210324711927");
cityOp[9][7]=new Option("海门","210315312109");
cityOp[9][8]=new Option("淮安","210333011909");
cityOp[9][9]=new Option("淮阴","210333611902");
cityOp[9][10]=new Option("江都","210322611932");
cityOp[9][11]=new Option("姜堰","210323412008");
cityOp[9][12]=new Option("江阴","210315412017");
cityOp[9][13]=new Option("靖江","210320212017");
cityOp[9][14]=new Option("金坛","210314611933");
cityOp[9][15]=new Option("昆山","210312312057");
cityOp[9][16]=new Option("连云港","290343611910");
cityOp[9][17]=new Option("溧阳","210312611929");
cityOp[9][18]=new Option("南通","310320112051");
cityOp[9][19]=new Option("邳州","210341911759");
cityOp[9][20]=new Option("启乐","210314812139");
cityOp[9][21]=new Option("如皋","210322312033");
cityOp[9][22]=new Option("宿迁","210335811818");
cityOp[9][23]=new Option("苏州","240311912037");
cityOp[9][24]=new Option("太仓","210312712106");
cityOp[9][25]=new Option("泰兴","210321012001");
cityOp[9][26]=new Option("泰州","210323011954");
cityOp[9][27]=new Option("通州","210320512103");
cityOp[9][28]=new Option("吴江","210311012039");
cityOp[9][29]=new Option("无锡","230313412018");
cityOp[9][30]=new Option("兴化","210325611950");
cityOp[9][31]=new Option("新沂","210342211820");
cityOp[9][32]=new Option("徐州","210341511711");
cityOp[9][33]=new Option("盐在","210332212008");
cityOp[9][34]=new Option("扬中","250321411949");
cityOp[9][35]=new Option("扬州","250322311926");
cityOp[9][36]=new Option("宜兴","210312111949");
cityOp[9][37]=new Option("仪征","210321611910");
cityOp[9][38]=new Option("张家港","210315212032");
cityOp[9][39]=new Option("镇江","210321111927");
cityOp[10] = new Array(34);
cityOp[10][0]=new Option("----","220301612010");
cityOp[10][1]=new Option("杭州","220301612010");
cityOp[10][2]=new Option("慈溪","220301112115");
cityOp[10][3]=new Option("东阳","220291612014");
cityOp[10][4]=new Option("奉化","220293912124");
cityOp[10][5]=new Option("富阳","220300311957");
cityOp[10][6]=new Option("海宁","220303212042");
cityOp[10][7]=new Option("湖州","220305212006");
cityOp[10][8]=new Option("建德","220292911916");
cityOp[10][9]=new Option("江山","220284511837");
cityOp[10][10]=new Option("嘉兴","220304612045");
cityOp[10][11]=new Option("金华","220290711939");
cityOp[10][12]=new Option("兰溪","220291211928");
cityOp[10][13]=new Option("临海","220285112108");
cityOp[10][14]=new Option("丽水","220282711954");
cityOp[10][15]=new Option("龙泉","220280411908");
cityOp[10][16]=new Option("宁波","220295212133");
cityOp[10][17]=new Option("平湖","220304212101");
cityOp[10][18]=new Option("衢州","220285811852");
cityOp[10][19]=new Option("瑞安","220274812038");
cityOp[10][20]=new Option("上虞","220300112052");
cityOp[10][21]=new Option("绍兴","320300012034");
cityOp[10][22]=new Option("台州","480284112127");
cityOp[10][23]=new Option("桐乡","480303812032");
cityOp[10][24]=new Option("温岭","480282212121");
cityOp[10][25]=new Option("温州","480280112039");
cityOp[10][26]=new Option("萧山","480300912016");
cityOp[10][27]=new Option("义乌","480291812004");
cityOp[10][28]=new Option("乐清","480280812058");
cityOp[10][29]=new Option("余杭","480302612018");
cityOp[10][30]=new Option("余姚","480300212110");
cityOp[10][31]=new Option("永康","480295412001");
cityOp[10][32]=new Option("舟山","480300112206");
cityOp[10][33]=new Option("诸暨","220294312014");
cityOp[11] = new Array(21);
cityOp[11][0]=new Option("----","190315211717");
cityOp[11][1]=new Option("合肥","190315211717");
cityOp[11][2]=new Option("安庆","260303111702");
cityOp[11][3]=new Option("蚌埠","190325611721");
cityOp[11][4]=new Option("亳州","190335211547");
cityOp[11][5]=new Option("巢湖","190313611752");
cityOp[11][6]=new Option("滁州","190321811818");
cityOp[11][7]=new Option("阜阳","190325411548");
cityOp[11][8]=new Option("贵池","190303911728");
cityOp[11][9]=new Option("淮北","190335711647");
cityOp[11][10]=new Option("淮南","190323711658");
cityOp[11][11]=new Option("黄山天都峰","272294311818");
cityOp[11][12]=new Option("界首","190331511521");
cityOp[11][13]=new Option("六安","190314411628");
cityOp[11][14]=new Option("马鞍山","190314311828");
cityOp[11][15]=new Option("明光","190324711758");
cityOp[11][16]=new Option("宿州","190333811658");
cityOp[11][17]=new Option("天长","190324111859");
cityOp[11][18]=new Option("铜陵","190305611748");
cityOp[11][19]=new Option("芜湖","190311911822");
cityOp[11][20]=new Option("宣州","190305711844");
cityOp[12] = new Array(23);
cityOp[12][0]=new Option("------","340260511918");
cityOp[12][1]=new Option("福州","340260511918");
cityOp[12][2]=new Option("长乐","340255811931");
cityOp[12][3]=new Option("福安","340270611939");
cityOp[12][4]=new Option("福清","340254211923");
cityOp[12][5]=new Option("建瓯","340270311820");
cityOp[12][6]=new Option("建阳","340272111807");
cityOp[12][7]=new Option("晋江","340244911835");
cityOp[12][8]=new Option("龙海","330242611748");
cityOp[12][9]=new Option("龙岩","330250611701");
cityOp[12][10]=new Option("南安","340245711823");
cityOp[12][11]=new Option("南平","340263811810");
cityOp[12][12]=new Option("宁德","340263911931");
cityOp[12][13]=new Option("莆田","380242611901");
cityOp[12][14]=new Option("泉州","380245611836");
cityOp[12][15]=new Option("三明","490261311736");
cityOp[12][16]=new Option("邵武","490272011729");
cityOp[12][17]=new Option("石狮","380244411838");
cityOp[12][18]=new Option("武夷山","490274611802");
cityOp[12][19]=new Option("厦门","380242711806");
cityOp[12][20]=new Option("永安","340255811723");
cityOp[12][21]=new Option("漳平","500251711724");
cityOp[12][22]=new Option("漳州","500243111739");
cityOp[13] = new Array(20);
cityOp[13][0]=new Option("------","500284011555");
cityOp[13][1]=new Option("南昌","500284011555");
cityOp[13][2]=new Option("德兴","500285711735");
cityOp[13][3]=new Option("丰城","500281211548");
cityOp[13][4]=new Option("赣州","580285211456");
cityOp[13][5]=new Option("高安","580282511522");
cityOp[13][6]=new Option("吉安","580270711458");
cityOp[13][7]=new Option("景德镇","280291711713");
cityOp[13][8]=new Option("井冈山","580263411410");
cityOp[13][9]=new Option("九江","550294311558");
cityOp[13][10]=new Option("乐平","580285811708");
cityOp[13][11]=new Option("临川","580275911621");
cityOp[13][12]=new Option("萍乡","580273711350");
cityOp[13][13]=new Option("瑞昌","580294011538");
cityOp[13][14]=new Option("瑞金","580255311601");
cityOp[13][15]=new Option("上饶","580252711758");
cityOp[13][16]=new Option("新余","580274811456");
cityOp[13][17]=new Option("宜春","580274711423");
cityOp[13][18]=new Option("鹰潭","580281411703");
cityOp[13][19]=new Option("樟树","580280311532");
cityOp[14] = new Array(48);
cityOp[14][0]=new Option("------","050364011700");
cityOp[14][1]=new Option("济南","050364011700");
cityOp[14][2]=new Option("安丘","050362511912");
cityOp[14][3]=new Option("滨州","050372211802");
cityOp[14][4]=new Option("昌邑","050395211924");
cityOp[14][5]=new Option("德州","050372611617");
cityOp[14][6]=new Option("东营","050372711830");
cityOp[14][7]=new Option("肥城","050361411646");
cityOp[14][8]=new Option("高密","050362211944");
cityOp[14][9]=new Option("菏泽","050351411526");
cityOp[14][10]=new Option("胶南","050355311958");
cityOp[14][11]=new Option("胶州","050361712000");
cityOp[14][12]=new Option("即墨","050362212028");
cityOp[14][13]=new Option("济宁","050352311633");
cityOp[14][14]=new Option("莱芜","050361211740");
cityOp[14][15]=new Option("莱西","050365212031");
cityOp[14][16]=new Option("莱阳","050365812042");
cityOp[14][17]=new Option("莱州","050371011957");
cityOp[14][18]=new Option("乐陵","050374411712");
cityOp[14][19]=new Option("聊城","050362611557");
cityOp[14][20]=new Option("临清","050365111542");
cityOp[14][21]=new Option("临沂","050350311820");
cityOp[14][22]=new Option("龙口","050373912021");
cityOp[14][23]=new Option("蓬莱","110374812045");
cityOp[14][24]=new Option("平度","050364711958");
cityOp[14][25]=new Option("青岛","060360312018");
cityOp[14][26]=new Option("青州","050364211828");
cityOp[14][27]=new Option("曲阜","050353611658");
cityOp[14][28]=new Option("日照","050352311932");
cityOp[14][29]=new Option("荣成","050371012225");
cityOp[14][30]=new Option("乳山","050365412131");
cityOp[14][31]=new Option("寿光","050365311844");
cityOp[14][32]=new Option("泰安","150361111708");
cityOp[14][33]=new Option("泰山岱顶","151361111708");
cityOp[14][34]=new Option("滕州","050350611709");
cityOp[14][35]=new Option("潍坊","130364311906");
cityOp[14][36]=new Option("威海","140373112207");
cityOp[14][37]=new Option("文登","050371212203");
cityOp[14][38]=new Option("新泰","050355411745");
cityOp[14][39]=new Option("烟台","110373212124");
cityOp[14][40]=new Option("兖州","050353211649");
cityOp[14][41]=new Option("禹城","050365611639");
cityOp[14][42]=new Option("枣庄","050345211733");
cityOp[14][43]=new Option("章丘","050364311732");
cityOp[14][44]=new Option("招远","050372112023");
cityOp[14][45]=new Option("诸城","050355911924");
cityOp[14][46]=new Option("淄博","120364811803");
cityOp[14][47]=new Option("邹城","120352411658");
cityOp[15] = new Array(37);
cityOp[15][0]=new Option("------","510344611340");
cityOp[15][1]=new Option("郑州","510344611340");
cityOp[15][2]=new Option("安阳","510360611421");
cityOp[15][3]=new Option("长葛","510341211347");
cityOp[15][4]=new Option("登封","510342711302");
cityOp[15][5]=new Option("邓州","510324211205");
cityOp[15][6]=new Option("巩义","510344611258");
cityOp[15][7]=new Option("鹤壁","510355411411");
cityOp[15][8]=new Option("辉县","510352711347");
cityOp[15][9]=new Option("焦作","510351411312");
cityOp[15][10]=new Option("济源","510350411235");
cityOp[15][11]=new Option("开封","560344711421");
cityOp[15][12]=new Option("灵宝","510343111052");
cityOp[15][13]=new Option("林州","510360311349");
cityOp[15][14]=new Option("漯河","510333311402");
cityOp[15][15]=new Option("洛阳","560344111227");
cityOp[15][16]=new Option("南阳","510330011232");
cityOp[15][17]=new Option("平顶山","510334411317");
cityOp[15][18]=new Option("濮阳","510354411501");
cityOp[15][19]=new Option("沁阳","510350511257");
cityOp[15][20]=new Option("汝州","510340911250");
cityOp[15][21]=new Option("三门峡","510344711112");
cityOp[15][22]=new Option("商丘","510342611538");
cityOp[15][23]=new Option("卫辉","510352411403");
cityOp[15][24]=new Option("舞钢","510331711330");
cityOp[15][25]=new Option("项城","510332611454");
cityOp[15][26]=new Option("荥阳","510344611321");
cityOp[15][27]=new Option("新密","510343111322");
cityOp[15][28]=new Option("新乡","510351811352");
cityOp[15][29]=new Option("信阳","510320711404");
cityOp[15][30]=new Option("新郑","510342411343");
cityOp[15][31]=new Option("许昌","510340111349");
cityOp[15][32]=new Option("偃师","510344311247");
cityOp[15][33]=new Option("义马","510344311155");
cityOp[15][34]=new Option("禹州","510340911328");
cityOp[15][35]=new Option("周口","510333711438");
cityOp[15][36]=new Option("驻马店","510325811401");
cityOp[16] = new Array(34);
cityOp[16][0]=new Option("------","520303511417");
cityOp[16][1]=new Option("武汉","520303511417");
cityOp[16][2]=new Option("安陆","520311511341");
cityOp[16][3]=new Option("当阳","520305011147");
cityOp[16][4]=new Option("丹江口","520323310830");
cityOp[16][5]=new Option("大冶","520300611458");
cityOp[16][6]=new Option("恩施","520301610929");
cityOp[16][7]=new Option("鄂州","520302311452");
cityOp[16][8]=new Option("广水","520313711348");
cityOp[16][9]=new Option("洪湖","520294811327");
cityOp[16][10]=new Option("黄石","520301211506");
cityOp[16][11]=new Option("黄州","520302711452");
cityOp[16][12]=new Option("荆门","570310211212");
cityOp[16][13]=new Option("荆沙","520301811216");
cityOp[16][14]=new Option("老河口","520322311140");
cityOp[16][15]=new Option("利川","520301810856");
cityOp[16][16]=new Option("麻城","520311011501");
cityOp[16][17]=new Option("浦圻","520294211351");
cityOp[16][18]=new Option("潜江","520302611253");
cityOp[16][19]=new Option("石首","520294311224");
cityOp[16][20]=new Option("十堰","520324011047");
cityOp[16][21]=new Option("随州","520314211322");
cityOp[16][22]=new Option("天门","520603911310");
cityOp[16][23]=new Option("武穴","520295111533");
cityOp[16][24]=new Option("襄樊","520320211208");
cityOp[16][25]=new Option("咸宁","520295311417");
cityOp[16][26]=new Option("仙桃","520302211327");
cityOp[16][27]=new Option("孝感","520305611354");
cityOp[16][28]=new Option("宜昌","570304211117");
cityOp[16][29]=new Option("宜城","570314211215");
cityOp[16][30]=new Option("应城","520305711333");
cityOp[16][31]=new Option("枣阳","520320711244");
cityOp[16][32]=new Option("枝城","520302311127");
cityOp[16][33]=new Option("钟祥","520311011234");
cityOp[17] = new Array(30);
cityOp[17][0]=new Option("------","530281211259");
cityOp[17][1]=new Option("长沙","530281211259");
cityOp[17][2]=new Option("常德","530290211151");
cityOp[17][3]=new Option("郴州","530254611302");
cityOp[17][4]=new Option("衡阳","530265311237");
cityOp[17][5]=new Option("洪江","530270710959");
cityOp[17][6]=new Option("怀化","590273310958");
cityOp[17][7]=new Option("津市","530293811152");
cityOp[17][8]=new Option("吉首","530281810943");
cityOp[17][9]=new Option("耒阳","530262411251");
cityOp[17][10]=new Option("冷水江","530274211126");
cityOp[17][11]=new Option("冷水滩","530262611135");
cityOp[17][12]=new Option("涟源","530274111141");
cityOp[17][13]=new Option("醴陵","530274011330");
cityOp[17][14]=new Option("临湘","530292911327");
cityOp[17][15]=new Option("浏阳","530280911337");
cityOp[17][16]=new Option("娄底","530274411159");
cityOp[17][17]=new Option("汨罗","530284911303");
cityOp[17][18]=new Option("韶山","530275411229");
cityOp[17][19]=new Option("邵阳","530271411128");
cityOp[17][20]=new Option("武冈","530264311037");
cityOp[17][21]=new Option("湘潭","530275211253");
cityOp[17][22]=new Option("湘乡","530274411231");
cityOp[17][23]=new Option("益阳","530283611220");
cityOp[17][24]=new Option("永州","530261311137");
cityOp[17][25]=new Option("沅江","530285011222");
cityOp[17][26]=new Option("岳阳","530292211306");
cityOp[17][27]=new Option("张家界","590290811029");
cityOp[17][28]=new Option("株洲","530275111309");
cityOp[17][29]=new Option("资兴","530255811313");
cityOp[18] = new Array(52);
cityOp[18][0]=new Option("----","370230811314");
cityOp[18][1]=new Option("广州","370230811314");
cityOp[18][2]=new Option("番禺","370225711322");
cityOp[18][3]=new Option("从化","370233311333");
cityOp[18][4]=new Option("花都","370232311312");
cityOp[18][5]=new Option("潮阳","390231611636");
cityOp[18][6]=new Option("潮州","390234011638");
cityOp[18][7]=new Option("澄海","390232811646");
cityOp[18][8]=new Option("东莞","370230211345");
cityOp[18][9]=new Option("恩平","370221211219");
cityOp[18][10]=new Option("佛山","370230211306");
cityOp[18][11]=new Option("南海","370230111309");
cityOp[18][12]=new Option("顺德","370225011315");
cityOp[18][13]=new Option("三水 ","370231811289");
cityOp[18][14]=new Option("高明","370225311250");
cityOp[18][15]=new Option("高要","370230211226");
cityOp[18][16]=new Option("高州","370215411050");
cityOp[18][17]=new Option("鹤山","370224611257");
cityOp[18][18]=new Option("河源","460234311441");
cityOp[18][19]=new Option("惠阳","460224811428");
cityOp[18][20]=new Option("惠州","460230511422");
cityOp[18][21]=new Option("江门","370223511304");
cityOp[18][22]=new Option("揭阳","460223211621");
cityOp[18][23]=new Option("开平","370222211240");
cityOp[18][24]=new Option("乐昌","370250911321");
cityOp[18][25]=new Option("雷州","450205411004");
cityOp[18][26]=new Option("廉江","450213711017");
cityOp[18][27]=new Option("连州","370244811223");
cityOp[18][28]=new Option("罗定","460224611133");
cityOp[18][29]=new Option("茂名","450214011053");
cityOp[18][30]=new Option("化州","450213911037");
cityOp[18][31]=new Option("梅州","390241911607");
cityOp[18][32]=new Option("普宁","390231811610");
cityOp[18][33]=new Option("清远","370234211301");
cityOp[18][34]=new Option("汕头","390232211641");
cityOp[18][35]=new Option("汕尾","460224711521");
cityOp[18][36]=new Option("韶关","370244811337");
cityOp[18][37]=new Option("四会","370232111241");
cityOp[18][38]=new Option("台山","370221511248");
cityOp[18][39]=new Option("吴川","450212611047");
cityOp[18][40]=new Option("新会","370223211301");
cityOp[18][41]=new Option("兴宁","370240911543");
cityOp[18][42]=new Option("阳春","370221011148");
cityOp[18][43]=new Option("阳江","370215011158");
cityOp[18][44]=new Option("英德","370241011322");
cityOp[18][45]=new Option("云浮","370225711202");
cityOp[18][46]=new Option("增城","370231811349");
cityOp[18][47]=new Option("湛江","450211111024");
cityOp[18][48]=new Option("肇庆","370230311227");
cityOp[18][49]=new Option("中山","440223111322");
cityOp[18][50]=new Option("珠海","440221711334");
cityOp[18][51]=new Option("深圳","400223311407");
cityOp[19] = new Array(17);
cityOp[19][0]=new Option("----","350224810819");
cityOp[19][1]=new Option("南宁","350224810819");
cityOp[19][2]=new Option("北海","430212810907");
cityOp[19][3]=new Option("北流","350224211021");
cityOp[19][4]=new Option("百色","350235410636");
cityOp[19][5]=new Option("防城港","430213710820");
cityOp[19][6]=new Option("贵港","350230610936");
cityOp[19][7]=new Option("桂林","410251711017");
cityOp[19][8]=new Option("桂平","410232211004");
cityOp[19][9]=new Option("河池","350244210803");
cityOp[19][10]=new Option("合山","350234710852");
cityOp[19][11]=new Option("柳州","350231910924");
cityOp[19][12]=new Option("赁祥","350220710644");
cityOp[19][13]=new Option("钦州","430215710837");
cityOp[19][14]=new Option("梧州","430232911120");
cityOp[19][15]=new Option("玉林","350223811009");
cityOp[19][16]=new Option("宜州","350242810840");
cityOp[20] = new Array(7);
cityOp[20][0]=new Option("----","360200211020");
cityOp[20][1]=new Option("海口","360200211020");
cityOp[20][2]=new Option("儋州","420193110934");
cityOp[20][3]=new Option("琼海","360191411028");
cityOp[20][4]=new Option("琼山","360195911021");
cityOp[20][5]=new Option("三亚","420181410931");
cityOp[20][6]=new Option("通什","360184610931");
cityOp[21] = new Array(6);
cityOp[21][0]=new Option("----","810293510633");
cityOp[21][1]=new Option("重庆","810293510633");
cityOp[21][2]=new Option("合川","810300210615");
cityOp[21][3]=new Option("江津","810291810616");
cityOp[21][4]=new Option("南川","810291010705");
cityOp[21][5]=new Option("永川","810292310553");
cityOp[22] = new Array(32);
cityOp[22][0]=new Option("----","830304010404");
cityOp[22][1]=new Option("成都","830304010404");
cityOp[22][2]=new Option("巴中","830315110643");
cityOp[22][3]=new Option("崇州","830303910340");
cityOp[22][4]=new Option("达川","830311410729");
cityOp[22][5]=new Option("德阳","830310910422");
cityOp[22][6]=new Option("都江堰","830310110337");
cityOp[22][7]=new Option("峨眉山","870293610329");
cityOp[22][8]=new Option("峨眉金顶","873293610329");
cityOp[22][9]=new Option("涪陵","830294210722");
cityOp[22][10]=new Option("广汉","880305810415");
cityOp[22][11]=new Option("广元","880322810551");
cityOp[22][12]=new Option("华蓥","830302610644");
cityOp[22][13]=new Option("简阳","830302410432");
cityOp[22][14]=new Option("江油","880314810442");
cityOp[22][15]=new Option("阆中","830313610558");
cityOp[22][16]=new Option("乐山","870293610344");
cityOp[22][17]=new Option("泸州","870285410524");
cityOp[22][18]=new Option("绵阳","880313010442");
cityOp[22][19]=new Option("南充","830304910604");
cityOp[22][20]=new Option("内江","830293610502");
cityOp[22][21]=new Option("攀枝花","830263410143");
cityOp[22][22]=new Option("彭州","830305910357");
cityOp[22][23]=new Option("邛崃","830302610328");
cityOp[22][24]=new Option("遂宁","830303110533");
cityOp[22][25]=new Option("万县","830305010821");
cityOp[22][26]=new Option("万源","830320310803");
cityOp[22][27]=new Option("西昌","830275410216");
cityOp[22][28]=new Option("雅安","860295910259");
cityOp[22][29]=new Option("宜宾","870284710434");
cityOp[22][30]=new Option("自贡","870292310446");
cityOp[22][31]=new Option("资阳","830300910438");
cityOp[23] = new Array(12);
cityOp[23][0]=new Option("----","840263510642");
cityOp[23][1]=new Option("贵阳","840263510642");
cityOp[23][2]=new Option("安顺","840261410555");
cityOp[23][3]=new Option("毕节","840271810518");
cityOp[23][4]=new Option("赤水","840283410542");
cityOp[23][5]=new Option("都匀","840261510731");
cityOp[23][6]=new Option("凯里","840263510758");
cityOp[23][7]=new Option("六盘水","840263510450");
cityOp[23][8]=new Option("清镇","840263310627");
cityOp[23][9]=new Option("铜仁","840274310912");
cityOp[23][10]=new Option("兴义","840250510453");
cityOp[23][11]=new Option("遵义","841274210655");
cityOp[24] = new Array(19);
cityOp[24][0]=new Option("----","821250410242");
cityOp[24][1]=new Option("昆明","821250410242");
cityOp[24][2]=new Option("保山","821250809910");
cityOp[24][3]=new Option("楚雄","821250110132");
cityOp[24][4]=new Option("大理","851253410013");
cityOp[24][5]=new Option("东川","821260610312");
cityOp[24][6]=new Option("个旧","821232110309");
cityOp[24][7]=new Option("景洪","820220110048");
cityOp[24][8]=new Option("开远","821234310313");
cityOp[24][9]=new Option("曲靖","821253010348");
cityOp[24][10]=new Option("瑞丽","820240009750");
cityOp[24][11]=new Option("思茅","820224810058");
cityOp[24][12]=new Option("畹町","820240609804");
cityOp[24][13]=new Option("宣威","821261310406");
cityOp[24][14]=new Option("玉溪","821242210232");
cityOp[24][15]=new Option("昭通","821272010342");
cityOp[24][16]=new Option("丽江","821268610025");
cityOp[24][17]=new Option("中甸","852277809972");
cityOp[24][18]=new Option("贡山","851277309865");
cityOp[25] = new Array(72);
cityOp[25][0]=new Option("----","804293909108");
cityOp[25][1]=new Option("拉萨","804293909108");
cityOp[25][2]=new Option("日喀则","805291608851");
cityOp[25][3]=new Option("林周","80430209124");
cityOp[25][4]=new Option("当雄","804305109105");
cityOp[25][5]=new Option("墨竹工卡","805297709177");
cityOp[25][6]=new Option("尼木","805294409014");
cityOp[25][7]=new Option("米林","805291809413");
cityOp[25][8]=new Option("墨脱","805292209526");
cityOp[25][9]=new Option("达孜","805296309139");
cityOp[25][10]=new Option("曲水","805293909070");
cityOp[25][11]=new Option("堆龙德庆","805296709096");
cityOp[25][12]=new Option("林芝","805295909425");
cityOp[25][13]=new Option("工布江达","805299209325");
cityOp[25][14]=new Option("那曲","804314709210");
cityOp[25][15]=new Option("巴青","804319609410");
cityOp[25][16]=new Option("比如","804315309368");
cityOp[25][17]=new Option("班戈","804313509005");
cityOp[25][18]=new Option("聂荣","804310809230");
cityOp[25][19]=new Option("索县","804319209371");
cityOp[25][20]=new Option("安多","804322909168");
cityOp[25][21]=new Option("申扎","804309408870");
cityOp[25][22]=new Option("吕都","804311809714");
cityOp[25][23]=new Option("贡觉","804308609829");
cityOp[25][24]=new Option("左贡","805296809790");
cityOp[25][25]=new Option("察隅","805286209749");
cityOp[25][26]=new Option("洛隆","804308109576");
cityOp[25][27]=new Option("丁青","804314209563");
cityOp[25][28]=new Option("波密","805299209575");
cityOp[25][29]=new Option("江达","804315308919");
cityOp[25][30]=new Option("察雅","804306909756");
cityOp[25][31]=new Option("芒康","805296409868");
cityOp[25][32]=new Option("八宿","804300409695");
cityOp[25][33]=new Option("边坝","804309409469");
cityOp[25][34]=new Option("类乌齐","804312009657");
cityOp[25][35]=new Option("乃东","805291809176");
cityOp[25][36]=new Option("加查","805290909260");
cityOp[25][37]=new Option("曲松","805290809211");
cityOp[25][38]=new Option("错那","805279809191");
cityOp[25][39]=new Option("穷结","805290409165");
cityOp[25][40]=new Option("贡嘎","805292509096");
cityOp[25][41]=new Option("浪卡子","805299609033");
cityOp[25][42]=new Option("桑日","805292609200");
cityOp[25][43]=new Option("朗县","805290609311");
cityOp[25][44]=new Option("隆子","805284609242");
cityOp[25][45]=new Option("措美","805284909140");
cityOp[25][46]=new Option("洛扎","805284209083");
cityOp[25][47]=new Option("扎囊","805292209126");
cityOp[25][48]=new Option("定结","805283808777");
cityOp[25][49]=new Option("拉孜","805291008762");
cityOp[25][50]=new Option("聂拉木","805281908594");
cityOp[25][51]=new Option("谢通门","805294308825");
cityOp[25][52]=new Option("仲巴","805296608415");
cityOp[25][53]=new Option("康马","805285708967");
cityOp[25][54]=new Option("亚东","805275508893");
cityOp[25][55]=new Option("岗巴","805282908850");
cityOp[25][56]=new Option("南木林","805297108902");
cityOp[25][57]=new Option("萨迦","805288708800");
cityOp[25][58]=new Option("定日","805285708711");
cityOp[25][59]=new Option("吉隆","805289408529");
cityOp[25][60]=new Option("昂仁","805293008722");
cityOp[25][61]=new Option("江孜","805289408963");
cityOp[25][62]=new Option("仁布","805292108977");
cityOp[25][63]=new Option("白朗","805291108916");
cityOp[25][64]=new Option("萨嘎","805293808530");
cityOp[25][65]=new Option("噶尔","804320808000");
cityOp[25][66]=new Option("革吉","804324508113");
cityOp[25][67]=new Option("扎达","804314707976");
cityOp[25][68]=new Option("措勤","804310608516");
cityOp[25][69]=new Option("日上","804334407961");
cityOp[25][70]=new Option("改则","804323308410");
cityOp[25][71]=new Option("普兰","804303708118");
cityOp[26] = new Array(14);
cityOp[26][0]=new Option("----","730341710857");
cityOp[26][1]=new Option("西安","730341710857");
cityOp[26][2]=new Option("安康","730324110901");
cityOp[26][3]=new Option("宝鸡","730342210709");
cityOp[26][4]=new Option("韩城","730352811027");
cityOp[26][5]=new Option("汉中","730330410701");
cityOp[26][6]=new Option("华阴","770343411005");
cityOp[26][7]=new Option("商州","730335210957");
cityOp[26][8]=new Option("铜川","730350610907");
cityOp[26][9]=new Option("渭南","730343010930");
cityOp[26][10]=new Option("咸阳","730342010843");
cityOp[26][11]=new Option("兴平","730341810829");
cityOp[26][12]=new Option("延安","780363510928");
cityOp[26][13]=new Option("榆林","730381810947");
cityOp[27] = new Array(14);
cityOp[27][0]=new Option("----","750360410351");
cityOp[27][1]=new Option("兰州","750360410351");
cityOp[27][2]=new Option("白银","750363310412");
cityOp[27][3]=new Option("敦煌","76040089441");
cityOp[27][4]=new Option("嘉峪关","76039489814");
cityOp[27][5]=new Option("金昌","750382810210");
cityOp[27][6]=new Option("酒泉","76039449831");
cityOp[27][7]=new Option("临夏","750353710312");
cityOp[27][8]=new Option("平凉","750353210640");
cityOp[27][9]=new Option("天水","760343710542");
cityOp[27][10]=new Option("武威","760375610239");
cityOp[27][11]=new Option("西峰","750354510740");
cityOp[27][12]=new Option("玉门","76039499735");
cityOp[27][13]=new Option("张掖","760385610026");
cityOp[28] = new Array(9);
cityOp[28][0]=new Option("-----","742363810174");
cityOp[28][1]=new Option("西 宁","742363810174");
cityOp[28][2]=new Option("德令哈","742372209723");
cityOp[28][3]=new Option("格尔木","742362609455");
cityOp[28][4]=new Option("大 通","742369210167");
cityOp[28][5]=new Option("都 兰","742363009813");
cityOp[28][6]=new Option("平 安","742364710209");
cityOp[28][7]=new Option("湟 中","742364910157");
cityOp[28][8]=new Option("乐 都","742364910238");
cityOp[28][9]=new Option("民 和","742363010280");
cityOp[28][10]=new Option("湟 源","742367210128");
cityOp[28][11]=new Option("互 助","742368410195");
cityOp[28][12]=new Option("化 隆","742361110230");
cityOp[28][13]=new Option("循 化","742358410246");
cityOp[28][14]=new Option("门 源","742373710162");
cityOp[28][15]=new Option("海 晏","742368910099");
cityOp[28][16]=new Option("刚 察","742373210017");
cityOp[28][17]=new Option("祁 连","742382010022");
cityOp[28][18]=new Option("同 仁","742355410200");
cityOp[28][19]=new Option("尖 扎","742359210200");
cityOp[28][20]=new Option("泽 库","742350310150");
cityOp[28][21]=new Option("河 南","742347510162");
cityOp[28][22]=new Option("共 和","742362710061");
cityOp[28][23]=new Option("贵 德","742360210147");
cityOp[28][24]=new Option("贵 南","742355710075");
cityOp[28][25]=new Option("同 德","742352410063");
cityOp[28][26]=new Option("兴 海","742356009999");
cityOp[28][27]=new Option("玛 沁","742344910026");
cityOp[28][28]=new Option("甘 德","742339509989");
cityOp[28][29]=new Option("久 治","742334610147");
cityOp[28][30]=new Option("班 玛","742329210073");
cityOp[28][31]=new Option("达 日","742337409968");
cityOp[28][32]=new Option("玛 多","742349209826");
cityOp[28][33]=new Option("玉 树","742330309697");
cityOp[28][34]=new Option("称 多","742333509712");
cityOp[28][35]=new Option("囊 谦","742322309647");
cityOp[28][36]=new Option("杂 多","742329209530");
cityOp[28][37]=new Option("治 多","742338609560");
cityOp[28][38]=new Option("曲麻菜","742345209550");
cityOp[28][39]=new Option("格尔木","742364109490");
cityOp[28][40]=new Option("乌 兰","742369009846");
cityOp[28][41]=new Option("都 兰","742363009813");
cityOp[28][42]=new Option("天 峻","742372809903");
cityOp[29] = new Array(19);
cityOp[29][0]=new Option("------","721382710616");
cityOp[29][1]=new Option("银川","721382710616");
cityOp[29][2]=new Option("青铜峡","721375610559");
cityOp[29][3]=new Option("石嘴山","721390210622");
cityOp[29][4]=new Option("吴忠","721375910611");
cityOp[29][5]=new Option("永 宁","721382810624");
cityOp[29][6]=new Option("贺 兰","721385510635");
cityOp[29][7]=new Option("平 罗","721389110654");
cityOp[29][8]=new Option("陶 乐","721388210669");
cityOp[29][9]=new Option("同 心","721369710594");
cityOp[29][10]=new Option("灵 武","721381010634");
cityOp[29][11]=new Option("中 宁","721374810566");
cityOp[29][12]=new Option("盐 池","721377810741");
cityOp[29][13]=new Option("中 卫","721375110518");
cityOp[29][14]=new Option("固 原","721360110628");
cityOp[29][15]=new Option("西 吉","721359710570");
cityOp[29][16]=new Option("泾 源","721355010633");
cityOp[29][17]=new Option("海 原","721365610564");
cityOp[29][18]=new Option("隆 德","721356310611");
cityOp[30] = new Array(18);
cityOp[30][0]=new Option("--------","711434508736");
cityOp[30][1]=new Option("乌鲁木齐","711434508736");
cityOp[30][2]=new Option("阿克苏","711410908019");
cityOp[30][3]=new Option("阿勒泰","711475008812");
cityOp[30][4]=new Option("阿图什","711394207608");
cityOp[30][5]=new Option("博乐","711445708208");
cityOp[30][6]=new Option("昌吉","711440208718");
cityOp[30][7]=new Option("阜康","711440908758");
cityOp[30][8]=new Option("哈密","711425009328");
cityOp[30][9]=new Option("和田","711370907955");
cityOp[30][10]=new Option("克拉玛依","711453608451");
cityOp[30][11]=new Option("喀什","791393007559");
cityOp[30][12]=new Option("库尔勒","711414608607");
cityOp[30][13]=new Option("奎屯","711442708456");
cityOp[30][14]=new Option("石河子","711441808600");
cityOp[30][15]=new Option("塔城","711464608259");
cityOp[30][16]=new Option("吐鲁番","711425408911");
cityOp[30][17]=new Option("伊宁","791435508120");
cityOp[31] = new Array(11);
cityOp[31][0]=new Option("-----","890222011410");
cityOp[31][1]=new Option("香 港","890222011410");
cityOp[31][2]=new Option("澳 门","900221311333");
cityOp[31][3]=new Option("台 北","910250512150");
cityOp[31][4]=new Option("高 雄","920226412037");
cityOp[31][5]=new Option("基 隆","910251412173");
cityOp[31][6]=new Option("台 中","910241512067");
cityOp[31][7]=new Option("台 南","910229812019");
cityOp[31][8]=new Option("宜 兰","910247512175");
cityOp[31][9]=new Option("桃 园","910250012130");
cityOp[31][10]=new Option("新 竹","910248112096");

function window_onload() {
	addProvince(CLD.province);
	addCity(CLD.province,CLD.city);
	CLD.province.disabled = false;
	CLD.city.disabled = false;
}

function addProvince(prov){
	for(i=0;i<PROVNUM;i++) {
		prov.add(provinceOp[i]);
	}
	prov.options[8].selected=true;
}

function addCity(prov,city,w){
	var i = prov.selectedIndex;
	for(j=0;j<cityOp[i].length;j++) {
		city.add(cityOp[i][j]);
	}
	if(w==0){
		city.options[w].selected=true;
	} else {
		city.options[1].selected=true;
	}
}

function delCity(city){
	var len = city.length;
	for(i=0;i<len;i++){
		city.remove(0);
	}
}

function province_onchange() {
	delCity(CLD.city);
	addCity(CLD.province,CLD.city,0);
}

function mychange() {
	document.CLD.per_des=document.CLD.city.value;
	Main(7,7);
	MoonRise(7,7);
}

function deltaT(year){
	return (58 + 0.8 * (year - 1990));
}

function MoonLong(T) {
	var kaku,i,ans;
	T /= 36525.0;
	ans = 0.0;
	for (i = 62; i >= 1 ; i--) {
		kaku = (mlb[i] * T + mlc[i]) * d2r;
		ans += mla[i] * Math.cos(kaku);
	}
	kaku = (mlb[0] * T + mlc[0] * d2r);
	ans += mla[0] * T * Math.cos(kaku);
	ans = ans - Math.floor(ans / 360.0) * 360.0;
	return ans;
}

function MoonLat(T) {
	var kaku,i,ans;
	T /= 36525.0;
	ans = 0.0;
	for (i = 44; i >= 0 ; i--) {
		kaku = (mab[i] * T + mac[i]) * d2r;
		ans += maa[i] * Math.cos(kaku);
	}
	return ans;
}

function MoonHP(T) {
	var kaku,i,ans;
	T /= 36525.0;
	ans = 0.0;
	for (i = 43; i >= 0 ; i--) {
		kaku = (mhb[i] * T + mhc[i]) * d2r;
		ans += mha[i] * Math.cos(kaku);
	}
return ans;
}

function GetEpci(T) {
	var Epci;
	T /= 36525.0;
	Epci = 0.00256 * Math.cos((1934 * T + 235) * d2r) + 0.00015 * Math.cos((72002 * T + 201) * d2r);
	Epci += 23.43928 - 0.01301 * T;
	return Epci * d2r;
}

function GetGMT(UT,T) {
	var gmt;
	gmt = UT + 6.69736 + 24.000513 * T / 365.25;
	gmt += 0.00029 * Math.sin((1934 * T / 36525 + 235) * d2r);
	gmt = gmt / 24.0;
	gmt = (gmt -  Math.floor(gmt)) * 24.0;
	return gmt;
}

function POL2XYZ(p,x) {
	with(Math) {
		x[2] = p[0] * sin(p[2]);
		x[1] = p[0] * cos(p[2]);
		x[0] = x[1] * cos(p[1]);
		x[1] *= sin(p[1]);
	}
}

function XYZ2POL(x,p) {
	var r;
	r = x[0] * x[0] + x[1] * x[1];
	with(Math) {
		p[0] = sqrt(x[2] * x[2] + r);
		r = sqrt(r);

		p[2] = atan2(x[2],r);
		p[1] = atan2(x[1],x[0]);
	}
}

function ROTXYZ(x,p,base) {
	var nx = new Array(3);
	var e1,e2;

	if (base == 0) {e1 = 1; e2 = 2;}
	else if (base == 1) {e1 = 0; e2 = 2;}
	else {e1 = 0; e2 = 1;}

	with(Math) {
		nx[base] = x[base];
		nx[e1] = cos(p) * x[e1] - sin(p) * x[e2];
		nx[e2] = sin(p) * x[e1] + cos(p) * x[e2];
	}
	for (e1 in nx) x[e1] = nx[e1];
}

function Koudou2Sekidou(Kou,Sek,T,FLAG) {
	var e;
	var xyz = new Array(3);

	e = GetEpci(T);
	if (FLAG != 1) e = -e;
	POL2XYZ(Kou,xyz);
	ROTXYZ(xyz,e,0);
	XYZ2POL(xyz,Sek);
}

var Now_dcd;

function GetEL(T,ra,dc,lng,lat) {
	var hangl,el,latd,hour;
	hour = T - 0.5;
	hour = (hour - Math.floor(hour)) * 24.0;
	hangl = GetGMT(hour,T) * 15.0 * d2r;
	hangl = ra - (hangl + lng);
	Now_dcd = 90 * d2r - dc;
	latd = 90 * d2r - lat;
	with (Math) {
		el = cos(latd) * cos(Now_dcd) + sin(latd) * sin(Now_dcd) * cos(hangl);
		el = atan2(sqrt(1.0 - el * el),el);
	}
	return Math.PI/2.0 - el;
}

function addZero(n) {
	if (n < 10) return '0' + n;
	else return '' + n;
}

function RiseSB(ts,dtime,lng,lat,objectflag) {
	var dtimemin,TD;
	var firstflag;
	var dt,t0,t1;
	var el0,el1;
	var elbase;
	var Kou = new Array(3);
	var Sek = new Array(3);
	firstflag = 1;
	dt = deltaT(yy) / 86400.0;
	dtimemin = 0.5 / 1440;
	t1 = ts;
	el0 = 0;
	while (dtime >= dtimemin) {
		TD = t1 + dt;
		if (objectflag == 1) {
			Kou[0] = 1.0;
			Kou[1] = SunLong(TD) * d2r;
			Kou[2] = 0.0;
			elbase = 0.902 * d2r;
		} else {
			elbase = MoonHP(TD);
			Kou[0] = 1.0 / Math.sin(elbase);
			Kou[1] = MoonLong(TD) * d2r;
			Kou[2] = MoonLat(TD) * d2r;
			elbase = (0.586 - elbase) * d2r;
		}

		Koudou2Sekidou(Kou,Sek,TD,1);
		el1 = GetEL(t1,Sek[1],Sek[2],lng,lat) + elbase;
		if (firstflag > 0) {
			el0 = el1;
			firstflag = 0;
		}
		if (el0 * el1 >= 0) {
			t0 = t1;
			el0 = el1;
		} else {
			dtime /= 2.0;
		}
		t1 = t0 + dtime;
	}
	RiseFlag = el1;
	return (t0 + dtime);
}

function MoonRise(v,Te) {
	var T,tmp,moont,moond,XZ,sitelong,sitelat,jwnum;
  var sObj=eval('SD'+ v);
  var d=sObj.innerHTML-1;
	if(sObj.innerHTML!='') {
		yy=cld[d].sYear;
		mm=cld[d].sMonth;
		jwnum=CLD.per_des;
		if(jwnum==undefined){
			sitelong = 113.07;
			sitelat = 23.02;
		}else{
			xZ=getString(jwnum,5);
			if(XZ==0) XZ='';
			sitelong=(XZ+''+getString(jwnum,4)+''+getString(jwnum,3)+'.'+getString(jwnum,2)+''+getString(jwnum,1))*1;
			sitelat=(getString(jwnum,9)+''+getString(jwnum,8)+'.'+getString(jwnum,7)+''+getString(jwnum,6))*1;
		}
		if(Te==7){
			dd =tD;
		} else {
			dd =cld[d].sDay;
		}
	}
	RiseTime = -999;
	SetTime = -999;
	T = Ymd2Jd(yy,mm,dd) - Ymd2Jd(2000,1,1.5) - 9/24.0;
	tmp = (RiseSB(T,(8.0/24.0),(sitelong * d2r),(sitelat * d2r),2) - T) * 24.0;
	if (RiseFlag < 0) {
		tmp = (RiseSB((T + (tmp + 1)/24),(8.0/24.0),(sitelong * d2r),(sitelat * d2r),2) - T) * 24.0;
	}
	RiseTime = tmp;

	tmp = (RiseSB(T,(8.0/24.0),(sitelong * d2r),(sitelat * d2r),2) - T) * 24.0;
	if (RiseFlag >= 0) {
		tmp = (RiseSB((T + (tmp + 0.5)/24),(8.0/24.0),(sitelong * d2r),(sitelat * d2r),2) - T) * 24.0;
	}
	SetTime = tmp;
	RiseTime += 0.5 / 1440.0;
	SetTime += 0.5 / 1440.0;
	if ((RiseTime >= 0) && (RiseTime < 24.0)) {
		hour = Math.floor(RiseTime);
		min = Math.floor((RiseTime - hour) * 60.0);
		moont='◎月出：'+addZero(hour) + '时' + addZero(min) + '分<br>';
	} else {
		moont='◎月出：00时00分<br>';
	}
	if ((SetTime >= 0) && (SetTime < 24.0)) {
		hour = Math.floor(SetTime);
		min = Math.floor((SetTime - hour) * 60.0);
		moond='◎月落：'+addZero(hour) + '时' + addZero(min) + '分';
	} else {
		moond='◎月落：00时00分';
	}
	rcrl2.innerHTML ='<br>'+moont + moond;
}
