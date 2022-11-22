-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: myhouse
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station` (
  `line` text,
  `name` text,
  `code` text,
  `lat` text,
  `lng` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES ('01호선','녹양','1908','37.75938','127.042292'),('01호선','남영','1002','37.541021','126.9713'),('01호선','용산','1003','37.529849','126.964561'),('01호선','노량진','1004','37.514219','126.942454'),('01호선','대방','1005','37.513342','126.926382'),('01호선','영등포','1006','37.515504','126.907628'),('01호선','신도림','1007','37.508725','126.891295'),('01호선','회기','1015','37.58946','127.057583'),('01호선','외대앞','1016','37.596073','127.063549'),('01호선','신이문','1017','37.601854','127.067325'),('01호선','광운대','1019','37.623632','127.061835'),('01호선','월계','1020','37.633212','127.058831'),('01호선','녹천','1021','37.644799','127.051269'),('01호선','창동','1022','37.653166','127.047731'),('01호선','신길','1032','37.517122','126.917169'),('01호선','석계','1018','37.614805','127.065851'),('01호선','봉명','1401','36.801215','127.135763'),('01호선','쌍용','1402','36.793759','127.1214'),('01호선','아산','1403','36.792053','127.104361'),('01호선','배방','1405','36.777629','127.052991'),('01호선','온양온천','1407','36.780483','127.003249'),('01호선','신창','1408','36.769502','126.951108'),('01호선','구로','1701','37.503039','126.881966'),('01호선','가산디지털단지','1702','37.481072','126.882343'),('01호선','금천구청','1703','37.455626','126.89398'),('01호선','관악','1705','37.419232','126.908706'),('01호선','안양','1706','37.401592','126.922874'),('01호선','명학','1707','37.384653','126.935433'),('01호선','금정','1708','37.372221','126.943429'),('01호선','의왕','1710','37.320852','126.948217'),('01호선','성균관대','1711','37.300349','126.97075'),('01호선','석수','1704','37.435047','126.902295'),('01호선','화서','1712','37.283862','126.989627'),('01호선','수원','1713','37.265974','126.999874'),('01호선','독산','1714','37.466613','126.889249'),('01호선','세류','1715','37.245025','127.013222'),('01호선','병점','1716','37.207503','127.032731'),('01호선','세마','1717','37.187533','127.04318'),('01호선','오산대','1718','37.168953','127.063197'),('01호선','송탄','1721','37.075696','127.054301'),('01호선','서정리','1722','37.056496','127.052819'),('01호선','평택','1724','36.990726','127.085159'),('01호선','성환','1725','36.916076','127.126964'),('01호선','직산','1726','36.870593','127.143904'),('01호선','두정','1727','36.833705','127.14896'),('01호선','천안','1728','36.810005','127.146826'),('01호선','광명','1750','37.416182','126.884466'),('01호선','개봉','1801','37.494594','126.85868'),('01호선','오류동','1802','37.494526','126.845365'),('01호선','역곡','1803','37.485178','126.811502'),('01호선','군포','1709','37.35356','126.948462'),('01호선','당정','1729','37.344285','126.948345'),('01호선','서동탄','1749','37.195504','127.051672'),('01호선','부천','1804','37.48405','126.782686'),('01호선','송내','1805','37.4876','126.753664'),('01호선','부평','1806','37.489493','126.724805'),('01호선','백운','1807','37.483664','126.707704'),('01호선','주안','1809','37.464941','126.679923'),('01호선','제물포','1810','37.466769','126.656666'),('01호선','동인천','1811','37.475276','126.632802'),('01호선','인천','1812','37.476691','126.616936'),('01호선','구일','1813','37.496756','126.870793'),('01호선','소사','1814','37.482753','126.79544'),('01호선','부개','1815','37.488418','126.74109'),('01호선','간석','1816','37.464737','126.694181'),('01호선','도원','1817','37.468446','126.642706'),('01호선','온수','1821','37.492258','126.823388'),('01호선','중동','1822','37.486562','126.764843'),('01호선','오산','1719','37.145885','127.06672'),('01호선','진위','1720','37.109447','127.062278'),('01호선','동암','1808','37.471408','126.702896'),('01호선','도화','1823','37.46607','126.668672'),('01호선','방학','1901','37.667503','127.044273'),('01호선','도봉','1902','37.679563','127.045595'),('01호선','도봉산','1903','37.689313','127.046222'),('01호선','망월사','1904','37.709914','127.047455'),('01호선','회룡','1905','37.724846','127.046895'),('01호선','의정부','1906','37.738415','127.045958'),('01호선','가능','1907','37.748577','127.044213'),('01호선','양주','1909','37.774381','127.044708'),('01호선','덕계','1910','37.818486','127.056486'),('01호선','덕정','1911','37.843188','127.061277'),('01호선','지행','1912','37.892334','127.055716'),('01호선','동두천중앙','1913','37.901885','127.056482'),('01호선','동두천','1915','37.927878','127.05479'),('01호선','보산','1914','37.913702','127.057277'),('01호선','종로3가','153','37.571607','126.991806'),('01호선','종로5가','154','37.570926','127.001849'),('01호선','동대문','155','37.57142','127.009745'),('01호선','신설동','156','37.575297','127.025087'),('01호선','제기동','157','37.578103','127.034893'),('01호선','청량리','158','37.580178','127.046835'),('01호선','동묘앞','159','37.572627','127.016429'),('01호선','소요산','1916','37.9481','127.061034'),('01호선','지제','1723','37.0188','127.070444'),('01호선','서울','150','37.554648','126.972559'),('01호선','시청','151','37.564718','126.977108'),('01호선','종각','152','37.570161','126.982923'),('02호선','강변','214','37.535095','127.094681'),('02호선','잠실나루','215','37.520733','127.10379'),('02호선','잠실','216','37.51395','127.102234'),('02호선','잠실새내','217','37.511687','127.086162'),('02호선','종합운동장','218','37.510997','127.073642'),('02호선','삼성','219','37.508844','127.06316'),('02호선','선릉','220','37.504503','127.049008'),('02호선','역삼','221','37.500622','127.036456'),('02호선','강남','222','37.497175','127.027926'),('02호선','교대','223','37.493415','127.01408'),('02호선','방배','225','37.481426','126.997596'),('02호선','사당','226','37.47653','126.981685'),('02호선','낙성대','227','37.47693','126.963693'),('02호선','서울대입구','228','37.481247','126.952739'),('02호선','봉천','229','37.482362','126.941892'),('02호선','신림','230','37.484201','126.929715'),('02호선','신대방','231','37.487462','126.913149'),('02호선','구로디지털단지','232','37.485266','126.901401'),('02호선','신도림','234','37.508725','126.891295'),('02호선','문래','235','37.517933','126.89476'),('02호선','영등포구청','236','37.52497','126.895951'),('02호선','용답','244','37.561904','127.050899'),('02호선','신정네거리','249','37.520074','126.852912'),('02호선','을지로입구','202','37.566014','126.982618'),('02호선','을지로3가','203','37.566295','126.99191'),('02호선','을지로4가','204','37.566941','126.998079'),('02호선','동대문역사문화공원','205','37.565138','127.007896'),('02호선','신당','206','37.565972','127.01782'),('02호선','한양대','209','37.555273','127.043655'),('02호선','뚝섬','210','37.547184','127.047367'),('02호선','성수','211','37.544581','127.055961'),('02호선','건대입구','212','37.540693','127.07023'),('02호선','구의','213','37.537077','127.085916'),('02호선','당산','237','37.53438','126.902281'),('02호선','합정','238','37.549463','126.913739'),('02호선','홍대입구','239','37.557192','126.925381'),('02호선','신촌','240','37.555134','126.936893'),('02호선','이대','241','37.556733','126.946013'),('02호선','아현','242','37.557345','126.956141'),('02호선','충정로','243','37.559973','126.963672'),('02호선','신답','245','37.57004','127.046481'),('02호선','까치산','200','37.531768','126.846683'),('02호선','시청','201','37.564718','126.977108'),('02호선','서초','224','37.491897','127.007917'),('02호선','왕십리','208','37.561533','127.037732'),('02호선','상왕십리','207','37.564354','127.029354'),('02호선','대림','233','37.49297','126.895801'),('02호선','신설동','246','37.575297','127.025087'),('02호선','도림천','247','37.514287','126.882768'),('02호선','양천구청','248','37.512398','126.865819'),('02호선','용두','250','37.574028','127.038091'),('03호선','양재','332','37.484147','127.034631'),('03호선','매봉','333','37.486947','127.046769'),('03호선','도곡','334','37.490858','127.055381'),('03호선','대치','335','37.494612','127.063642'),('03호선','학여울','336','37.496663','127.070594'),('03호선','대청','337','37.493514','127.079532'),('03호선','일원','338','37.483681','127.08439'),('03호선','수서','339','37.487371','127.10188'),('03호선','동대입구','322','37.559052','127.005602'),('03호선','금호','324','37.548034','127.015872'),('03호선','옥수','325','37.540685','127.017965'),('03호선','압구정','326','37.527072','127.028461'),('03호선','신사','327','37.516334','127.020114'),('03호선','잠원','328','37.512759','127.01122'),('03호선','고속터미널','329','37.50481','127.004943'),('03호선','교대','330','37.493415','127.01408'),('03호선','남부터미널','331','37.485013','127.016189'),('03호선','경복궁','317','37.575762','126.97353'),('03호선','안국','318','37.576477','126.985443'),('03호선','종로3가','319','37.571607','126.991806'),('03호선','을지로3가','320','37.566295','126.99191'),('03호선','지축','309','37.648048','126.913951'),('03호선','구파발','310','37.636763','126.918821'),('03호선','연신내','311','37.619001','126.921008'),('03호선','불광','312','37.610469','126.929887'),('03호선','녹번','313','37.600927','126.935756'),('03호선','홍제','314','37.589066','126.943736'),('03호선','무악재','315','37.582299','126.950291'),('03호선','독립문','316','37.574571','126.957748'),('03호선','가락시장','340','37.492522','127.118234'),('03호선','경찰병원','341','37.495918','127.12454'),('03호선','오금','342','37.502162','127.128111'),('03호선','원당','1951','37.653324','126.843041'),('03호선','대곡','1953','37.631626','126.811024'),('03호선','백석','1954','37.643114','126.78787'),('03호선','마두','1955','37.652206','126.77762'),('03호선','정발산','1956','37.659477','126.773359'),('03호선','주엽','1957','37.670072','126.761334'),('03호선','대화','1958','37.676087','126.747569'),('03호선','원흥','1948','37.650658','126.872642'),('03호선','삼송','1950','37.653083','126.895558'),('03호선','약수','323','37.55434','127.010655'),('03호선','충무로','321','37.561243','126.99428'),('03호선','화정','1952','37.634592','126.83265'),('04호선','남태령','434','37.463873','126.989134'),('04호선','금정','1458','37.372221','126.943429'),('04호선','당고개','409','37.670272','127.079066'),('04호선','상계','410','37.660878','127.073572'),('04호선','노원','411','37.655128','127.061368'),('04호선','창동','412','37.653166','127.047731'),('04호선','쌍문','413','37.648627','127.034709'),('04호선','수유','414','37.638052','127.025732'),('04호선','미아','415','37.62667','127.025983'),('04호선','미아사거리','416','37.613292','127.030053'),('04호선','길음','417','37.603407','127.025053'),('04호선','성신여대입구','418','37.592624','127.016403'),('04호선','한성대입구','419','37.588458','127.006221'),('04호선','혜화','420','37.582336','127.001844'),('04호선','동대문','421','37.57142','127.009745'),('04호선','동대문역사문화공원','422','37.565138','127.007896'),('04호선','충무로','423','37.561243','126.99428'),('04호선','명동','424','37.560989','126.986325'),('04호선','회현','425','37.558514','126.978246'),('04호선','서울','426','37.554648','126.972559'),('04호선','숙대입구','427','37.54456','126.972106'),('04호선','삼각지','428','37.534777','126.97311'),('04호선','신용산','429','37.52917','126.967894'),('04호선','동작','431','37.502971','126.979306'),('04호선','총신대입구','432','37.486263','126.981989'),('04호선','사당','433','37.47653','126.981685'),('04호선','경마공원','1451','37.443885','127.007888'),('04호선','대공원','1452','37.435675','127.006523'),('04호선','과천','1453','37.433021','126.996568'),('04호선','정부과천청사','1454','37.426513','126.98978'),('04호선','인덕원','1455','37.401553','126.976715'),('04호선','평촌','1456','37.394287','126.963883'),('04호선','범계','1457','37.389793','126.950806'),('04호선','선바위','1450','37.451673','127.002303'),('04호선','산본','1751','37.358101','126.933274'),('04호선','대야미','1752','37.328467','126.917332'),('04호선','반월','1753','37.312212','126.903524'),('04호선','상록수','1754','37.302795','126.866489'),('04호선','한대앞','1755','37.309689','126.85344'),('04호선','중앙','1756','37.315941','126.838573'),('04호선','고잔','1757','37.316777','126.823249'),('04호선','초지','1758','37.320646','126.805913'),('04호선','안산','1759','37.327082','126.788532'),('04호선','신길온천','1760','37.338212','126.765844'),('04호선','정왕','1761','37.351735','126.742989'),('04호선','오이도','1762','37.362357','126.738714'),('04호선','수리산','1763','37.349801','126.925365'),('04호선','이촌','430','37.522272','126.974345'),('05호선','종로3가','2535','37.571607','126.991806'),('05호선','둔촌동','2555','37.527788','127.136248'),('05호선','올림픽공원','2556','37.516078','127.130848'),('05호선','방화','2511','37.577446','126.812741'),('05호선','개화산','2512','37.572399','126.806171'),('05호선','김포공항','2513','37.562434','126.801058'),('05호선','송정','2514','37.561184','126.811973'),('05호선','마곡','2515','37.560183','126.825448'),('05호선','애오개','2531','37.553736','126.95682'),('05호선','서대문','2533','37.565773','126.966641'),('05호선','광화문','2534','37.571026','126.976669'),('05호선','충정로','2532','37.559973','126.963672'),('05호선','왕십리','2541','37.561533','127.037732'),('05호선','발산','2516','37.558598','126.837668'),('05호선','우장산','2517','37.548768','126.836318'),('05호선','화곡','2518','37.541513','126.840461'),('05호선','까치산','2519','37.531768','126.846683'),('05호선','신정','2520','37.524997','126.856191'),('05호선','목동','2521','37.526065','126.864931'),('05호선','오목교','2522','37.524496','126.875181'),('05호선','양평','2523','37.492832','127.491814'),('05호선','영등포구청','2524','37.52497','126.895951'),('05호선','영등포시장','2525','37.522669','126.905139'),('05호선','신길','2526','37.517122','126.917169'),('05호선','여의도','2527','37.521624','126.924191'),('05호선','여의나루','2528','37.527098','126.932901'),('05호선','마포','2529','37.539574','126.945932'),('05호선','공덕','2530','37.544018','126.951592'),('05호선','을지로4가','2536','37.566941','126.998079'),('05호선','신금호','2539','37.554548','127.020331'),('05호선','행당','2540','37.557322','127.029476'),('05호선','마장','2542','37.5661','127.042973'),('05호선','답십리','2543','37.566747','127.052704'),('05호선','장한평','2544','37.56144','127.064623'),('05호선','군자','2545','37.557121','127.079542'),('05호선','아차산','2546','37.551691','127.089761'),('05호선','광나루','2547','37.545303','127.10357'),('05호선','천호','2548','37.538397','127.123572'),('05호선','강동','2549','37.535804','127.132481'),('05호선','길동','2550','37.537801','127.140004'),('05호선','굽은다리','2551','37.545477','127.142853'),('05호선','명일','2552','37.55137','127.143999'),('05호선','고덕','2553','37.555004','127.154151'),('05호선','상일동','2554','37.556712','127.166417'),('05호선','방이','2557','37.508857','127.126133'),('05호선','오금','2558','37.502162','127.128111'),('05호선','개롱','2559','37.498079','127.13482'),('05호선','거여','2560','37.493105','127.14415'),('05호선','마천','2561','37.49499','127.152781'),('05호선','동대문역사문화공원','2537','37.565138','127.007896'),('05호선','청구','2538','37.560245','127.013828'),('06호선','망원','2622','37.556094','126.910052'),('06호선','합정','2623','37.549463','126.913739'),('06호선','상수','2624','37.547716','126.922852'),('06호선','광흥창','2625','37.547456','126.931993'),('06호선','대흥','2626','37.547771','126.942069'),('06호선','버티고개','2633','37.548013','127.007055'),('06호선','약수','2634','37.55434','127.010655'),('06호선','청구','2635','37.560245','127.013828'),('06호선','신당','2636','37.565972','127.01782'),('06호선','응암','2611','37.598605','126.915577'),('06호선','역촌','2612','37.606021','126.922744'),('06호선','불광','2613','37.610469','126.929887'),('06호선','독바위','2614','37.618456','126.933031'),('06호선','연신내','2615','37.619001','126.921008'),('06호선','구산','2616','37.611377','126.91727'),('06호선','새절','2617','37.591148','126.913629'),('06호선','증산','2618','37.583876','126.909645'),('06호선','디지털미디어시티','2619','37.576646','126.900984'),('06호선','월드컵경기장','2620','37.569532','126.899298'),('06호선','마포구청','2621','37.563515','126.903343'),('06호선','공덕','2627','37.544018','126.951592'),('06호선','효창공원앞','2628','37.539261','126.961351'),('06호선','삼각지','2629','37.534777','126.97311'),('06호선','녹사평','2630','37.534675','126.986695'),('06호선','이태원','2631','37.534488','126.994302'),('06호선','한강진','2632','37.539631','127.001725'),('06호선','동묘앞','2637','37.572627','127.016429'),('06호선','창신','2638','37.579661','127.015241'),('06호선','보문','2639','37.585286','127.019381'),('06호선','안암','2640','37.586272','127.029005'),('06호선','고려대','2641','37.590508','127.036296'),('06호선','월곡','2642','37.601948','127.041518'),('06호선','상월곡','2643','37.606377','127.048491'),('06호선','돌곶이','2644','37.610537','127.056431'),('06호선','석계','2645','37.614805','127.065851'),('06호선','태릉입구','2646','37.617983','127.07512'),('06호선','화랑대','2647','37.620064','127.084689'),('06호선','봉화산','2648','37.617283','127.091401'),('06호선','신내','2649','37.612887','127.103218'),('07호선','군자','2727','37.557121','127.079542'),('07호선','강남구청','2732','37.517186','127.04128'),('07호선','학동','2733','37.514229','127.031656'),('07호선','논현','2734','37.511093','127.021415'),('07호선','반포','2735','37.508178','127.011727'),('07호선','고속터미널','2736','37.50481','127.004943'),('07호선','내방','2737','37.487618','126.993513'),('07호선','이수','2738','37.486263','126.981989'),('07호선','까치울','2753','37.506207','126.810939'),('07호선','부천종합운동장','2754','37.50538','126.797337'),('07호선','춘의','2755','37.503663','126.787036'),('07호선','부평구청','2761','37.508336','126.720548'),('07호선','사가정','2724','37.580894','127.088478'),('07호선','용마산','2725','37.573647','127.086727'),('07호선','중곡','2726','37.565923','127.08432'),('07호선','어린이대공원','2728','37.548014','127.074658'),('07호선','남성','2739','37.484596','126.971251'),('07호선','숭실대입구','2740','37.496029','126.953822'),('07호선','상도','2741','37.502834','126.94791'),('07호선','장승배기','2742','37.504898','126.93915'),('07호선','신대방삼거리','2743','37.499701','126.928276'),('07호선','보라매','2744','37.499872','126.920428'),('07호선','신풍','2745','37.50008','126.90993'),('07호선','대림','2746','37.49297','126.895801'),('07호선','남구로','2747','37.486056','126.887249'),('07호선','가산디지털단지','2748','37.481072','126.882343'),('07호선','철산','2749','37.47605','126.867911'),('07호선','광명사거리','2750','37.479252','126.854876'),('07호선','천왕','2751','37.486637','126.838713'),('07호선','온수','2752','37.492258','126.823388'),('07호선','장암','2711','37.700109','127.053196'),('07호선','중화','2721','37.602545','127.079264'),('07호선','상봉','2722','37.596362','127.085032'),('07호선','면목','2723','37.588579','127.087503'),('07호선','건대입구','2729','37.540693','127.07023'),('07호선','뚝섬유원지','2730','37.53154','127.066704'),('07호선','청담','2731','37.519365','127.05335'),('07호선','도봉산','2712','37.689313','127.046222'),('07호선','수락산','2713','37.67785','127.055315'),('07호선','마들','2714','37.66494','127.057675'),('07호선','노원','2715','37.655128','127.061368'),('07호선','중계','2716','37.644583','127.064303'),('07호선','하계','2717','37.636352','127.06799'),('07호선','공릉','2718','37.625742','127.072896'),('07호선','태릉입구','2719','37.617983','127.07512'),('07호선','먹골','2720','37.610637','127.077725'),('07호선','신중동','2756','37.503048','126.77596'),('07호선','부천시청','2757','37.504631','126.763538'),('07호선','상동','2758','37.505781','126.753083'),('07호선','삼산체육관','2759','37.506411','126.742153'),('07호선','굴포천','2760','37.506997','126.73128'),('08호선','암사','2811','37.55021','127.127562'),('08호선','천호','2812','37.538397','127.123572'),('08호선','강동구청','2813','37.530341','127.120508'),('08호선','몽촌토성','2814','37.517409','127.112359'),('08호선','잠실','2815','37.51395','127.102234'),('08호선','신흥','2825','37.440918','127.147564'),('08호선','수진','2826','37.437428','127.140722'),('08호선','모란','2827','37.43213','127.129087'),('08호선','석촌','2816','37.505431','127.106979'),('08호선','송파','2817','37.499703','127.112183'),('08호선','가락시장','2818','37.492522','127.118234'),('08호선','문정','2819','37.485855','127.1225'),('08호선','장지','2820','37.478703','127.126191'),('08호선','복정','2821','37.470047','127.126662'),('08호선','산성','2822','37.457122','127.149908'),('08호선','남한산성입구','2823','37.451535','127.159816'),('08호선','단대오거리','2824','37.44521','127.156866'),('09호선','석촌','4133','37.505431','127.106979'),('09호선','올림픽공원','4136','37.516078','127.130848'),('09호선','선정릉','4127','37.51098','127.043593'),('09호선','삼성중앙','4128','37.513011','127.053282'),('09호선','봉은사','4129','37.514219','127.060245'),('09호선','종합운동장','4130','37.510997','127.073642'),('09호선','개화','4101','37.578608','126.798153'),('09호선','김포공항','4102','37.562434','126.801058'),('09호선','공항시장','4103','37.563726','126.810678'),('09호선','신방화','4104','37.567532','126.816601'),('09호선','마곡나루','4105','37.567336','126.829497'),('09호선','양천향교','4106','37.568381','126.841333'),('09호선','증미','4108','37.557402','126.861939'),('09호선','등촌','4109','37.550632','126.865689'),('09호선','선유도','4112','37.53802','126.893525'),('09호선','샛강','4116','37.517274','126.928422'),('09호선','노량진','4117','37.514219','126.942454'),('09호선','흑석','4119','37.50877','126.963708'),('09호선','구반포','4121','37.501364','126.987332'),('09호선','신반포','4122','37.503415','126.995925'),('09호선','신논현','4125','37.504598','127.02506'),('09호선','염창','4110','37.546936','126.874916'),('09호선','신목동','4111','37.544277','126.88308'),('09호선','당산','4113','37.53438','126.902281'),('09호선','국회의사당','4114','37.528105','126.917874'),('09호선','여의도','4115','37.521624','126.924191'),('09호선','노들','4118','37.512887','126.953222'),('09호선','동작','4120','37.502971','126.979306'),('09호선','고속터미널','4123','37.50481','127.004943'),('09호선','사평','4124','37.504206','127.015259'),('09호선','가양','4107','37.561391','126.854456'),('09호선','언주','4126','37.507287','127.033868'),('경강선','삼동','1503','37.409522','127.20336'),('경강선','경기광주','1504','37.399907','126.630347'),('경강선','초월','1505','37.374419','127.299'),('경강선','곤지암','1506','37.351315','127.34674'),('경강선','신둔도예촌','1507','37.317185','127.40476'),('경강선','이천','1508','37.265579','127.44226'),('경강선','부발','1509','37.260192','127.490277'),('경강선','여주','1511','37.282701','127.628607'),('경강선','세종대왕릉','1510','37.295309','127.570938'),('경강선','이매','1502','37.396104','127.12827'),('경강선','판교','1501','37.394761','127.111217'),('경의선','이촌','1008','37.522272','126.974345'),('경의선','서빙고','1009','37.519594','126.988537'),('경의선','옥수','1011','37.540685','127.017965'),('경의선','왕십리','1013','37.561533','127.037732'),('경의선','청량리','1014','37.580178','127.046835'),('경의선','중랑','1201','37.594917','127.076116'),('경의선','상봉','1202','37.596362','127.085032'),('경의선','망우','1203','37.59955','127.091909'),('경의선','양원','1204','37.606596','127.107906'),('경의선','구리','1205','37.603392','127.143869'),('경의선','도농','1206','37.608806','127.161153'),('경의선','양정','1207','37.60533','127.19364'),('경의선','덕소','1208','37.586781','127.208832'),('경의선','도심','1209','37.579622','127.222672'),('경의선','팔당','1210','37.547371','127.243939'),('경의선','운길산','1211','37.554669','127.310115'),('경의선','양수','1212','37.545981','127.329098'),('경의선','신원','1213','37.525545','127.372921'),('경의선','응봉','1012','37.549946','127.034538'),('경의선','국수','1214','37.516169','127.399367'),('경의선','아신','1215','37.51382','127.443173'),('경의선','오빈','1216','37.506062','127.473868'),('경의선','양평','1217','37.492832','127.491814'),('경의선','원덕','1218','37.468672','127.547076'),('경의선','용문','1219','37.48223','127.594647'),('경의선','지평','1220','37.476444','127.629617'),('경의선','서울','1251','37.554648','126.972559'),('경의선','신촌','1252','37.555134','126.936893'),('경의선','효창공원앞','1261','37.539261','126.961351'),('경의선','공덕','1262','37.544018','126.951592'),('경의선','홍대입구','1264','37.557192','126.925381'),('경의선','수색','1267','37.580842','126.895611'),('경의선','화전','1268','37.602888','126.868387'),('경의선','강매','1269','37.612314','126.843223'),('경의선','행신','1270','37.612102','126.834146'),('경의선','능곡','1271','37.618808','126.820783'),('경의선','곡산','1272','37.645676','126.801762'),('경의선','풍산','1274','37.672346','126.786243'),('경의선','일산','1275','37.682077','126.769846'),('경의선','탄현','1276','37.694023','126.761086'),('경의선','운정','1278','37.725826','126.767257'),('경의선','금릉','1279','37.751322','126.765347'),('경의선','월롱','1282','37.796188','126.792587'),('경의선','파주','1283','37.815298','126.792783'),('경의선','문산','1284','37.854619','126.788047'),('경의선','서강대','1263','37.551881','126.935711'),('경의선','백마','1273','37.658239','126.794461'),('경의선','야당','1277','37.712327','126.761356'),('경의선','금촌','1280','37.766217','126.774644'),('경의선','디지털미디어시티','1266','37.576646','126.900984'),('경의선','대곡','300','37.631626','126.811024'),('경의선','한남','1010','37.52943','127.009169'),('경의선','용산','None','37.529849','126.964561'),('경의선','회기','None','37.58946','127.057583'),('경의선','가좌','1265','37.568491','126.915487'),('경춘선','광운대','1305','37.623632','127.061835'),('경춘선','청량리','1306','37.580178','127.046835'),('경춘선','회기','1307','37.58946','127.057583'),('경춘선','상봉','1309','37.596362','127.085032'),('경춘선','망우','1310','37.59955','127.091909'),('경춘선','신내','1311','37.612887','127.103218'),('경춘선','갈매','1312','37.634118','127.114757'),('경춘선','별내','1313','37.64202','127.12684'),('경춘선','퇴계원','1314','37.648311','127.143952'),('경춘선','사릉','1315','37.65108','127.176933'),('경춘선','평내호평','1317','37.653225','127.244493'),('경춘선','마석','1319','37.652782','127.311767'),('경춘선','대성리','1320','37.684071','127.379319'),('경춘선','청평','1321','37.735488','127.42661'),('경춘선','상천','1322','37.770246','127.454821'),('경춘선','가평','1323','37.814536','127.510739'),('경춘선','굴봉산','1324','37.832067','127.557695'),('경춘선','백양리','1325','37.830779','127.58933'),('경춘선','강촌','1326','37.805723','127.634146'),('경춘선','김유정','1327','37.818466','127.71434'),('경춘선','남춘천','1328','37.864007','127.723792'),('경춘선','금곡','1316','37.637382','127.207853'),('경춘선','천마산','1318','37.658978','127.285379'),('경춘선','춘천','1329','37.885054','127.717023'),('경춘선','중랑','1308','37.594917','127.076116'),('공항철도','김포공항','4207','37.562434','126.801058'),('공항철도','계양','4208','37.571462','126.735637'),('공항철도','마곡나루','4206','37.567336','126.829497'),('공항철도','검암','4209','37.569104','126.673728'),('공항철도','서울','4201','37.554648','126.972559'),('공항철도','공덕','4202','37.544018','126.951592'),('공항철도','홍대입구','4203','37.557192','126.925381'),('공항철도','디지털미디어시티','4204','37.576646','126.900984'),('공항철도','청라국제도시','4210','37.555878','126.625327'),('공항철도','운서','4211','37.492904','126.49379'),('공항철도','공항화물청사','4212','37.458366','126.476241'),('공항철도','영종','4217','37.511466','126.5237'),('김포도시철도','김포공항','4929','37.562434','126.801058'),('분당선','선릉','1023','37.504503','127.049008'),('분당선','한티','1024','37.496237','127.052873'),('분당선','도곡','1025','37.490858','127.055381'),('분당선','구룡','1026','37.486839','127.058856'),('분당선','개포동','1027','37.489116','127.06614'),('분당선','대모산입구','1028','37.491373','127.07272'),('분당선','왕십리','None','37.561533','127.037732'),('분당선','수서','1030','37.487371','127.10188'),('분당선','복정','1031','37.470047','127.126662'),('분당선','서울숲','1847','37.543617','127.044707'),('분당선','압구정로데오','1848','37.527381','127.040534'),('분당선','강남구청','1849','37.517186','127.04128'),('분당선','선정릉','1850','37.51098','127.043593'),('분당선','가천대','1851','37.448605','127.126697'),('분당선','태평','1852','37.440019','127.127709'),('분당선','모란','1853','37.43213','127.129087'),('분당선','야탑','1854','37.411185','127.128715'),('분당선','서현','1855','37.385126','127.123592'),('분당선','수내','1856','37.378455','127.114322'),('분당선','정자','1857','37.36706','127.108105'),('분당선','미금','1858','37.350077','127.10891'),('분당선','이매','1860','37.396104','127.12827'),('분당선','신갈','1864','37.286102','127.111313'),('분당선','상갈','1866','37.26181','127.108847'),('분당선','청명','1867','37.259489','127.078934'),('분당선','영통','1868','37.251568','127.071394'),('분당선','망포','1869','37.245795','127.057353'),('분당선','매탄권선','1870','37.252759','127.040566'),('분당선','수원시청','1871','37.261911','127.030736'),('분당선','매교','1872','37.265481','127.015678'),('분당선','구성','1863','37.298969','127.105664'),('분당선','청량리','1845','37.580178','127.046835'),('분당선','보정','1861','37.312752','127.108196'),('분당선','오리','1859','37.339824','127.108942'),('분당선','죽전','1862','37.324753','127.107395'),('분당선','기흥','1865','37.275619','127.115936'),('분당선','수원','1846','37.265974','126.999874'),('서해선','소사','4804','37.482753','126.79544'),('서해선','초지','4813','37.320646','126.805913'),('수인선','오이도','1800','37.362357','126.738714'),('수인선','달월','1878','37.379681','126.745177'),('수인선','월곶','1879','37.391769','126.742699'),('수인선','소래포구','1880','37.40095','126.733522'),('수인선','인천논현','1881','37.400614','126.722478'),('수인선','호구포','1882','37.401637','126.708627'),('수인선','남동인더스파크','1883','37.407722','126.695216'),('수인선','원인재','1884','37.412603','126.687389'),('수인선','연수','1885','37.417804','126.67894'),('수인선','송도','1886','37.428514','126.657772'),('수인선','인하대','1888','37.448493','126.649619'),('수인선','숭의','1889','37.460789','126.638297'),('수인선','인천','1891','37.476691','126.616936'),('수인선','신포','1890','37.46874','126.623853'),('신분당선','동천','4314','37.337928','127.102976'),('신분당선','수지구청','4315','37.322702','127.095026'),('신분당선','성복','4316','37.313335','127.0801'),('신분당선','상현','4317','37.297664','127.069342'),('신분당선','광교중앙','4318','37.288617','127.051478'),('신분당선','정자','4312','37.36706','127.108105'),('신분당선','미금','4313','37.350077','127.10891'),('신분당선','양재시민의숲','4309','37.470023','127.03842'),('신분당선','판교','4311','37.394761','127.111217'),('신분당선','광교','4319','37.30211','127.044483'),('신분당선','강남','4307','37.497175','127.027926'),('신분당선','양재','4308','37.484147','127.034631'),('신분당선','청계산입구','4310','37.447211','127.055664'),('용인경전철','강남대','4502','37.270161','127.126033'),('용인경전철','지석','4503','37.269606','127.136515'),('용인경전철','어정','4504','37.274917','127.143714'),('용인경전철','동백','4505','37.269043','127.152716'),('용인경전철','초당','4506','37.260752','127.159443'),('용인경전철','시청.용인대','4509','37.239151','127.178406'),('용인경전철','명지대','4510','37.237964','127.190294'),('용인경전철','김량장','4511','37.237247','127.198781'),('용인경전철','운동장.송담대','4512','37.237845','127.209198'),('용인경전철','고진','4513','37.24484','127.214251'),('용인경전철','보평','4514','37.258965','127.218457'),('용인경전철','둔전','4515','37.267051','127.21364'),('용인경전철','삼가','4508','37.242115','127.168075'),('용인경전철','전대.에버랜드','4517','37.285342','127.219561'),('용인경전철','기흥','4501','37.275619','127.115936'),('우이신설경전철','북한산우이','4701','37.663146','127.012789'),('우이신설경전철','솔밭공원','4702','37.656088','127.013252'),('우이신설경전철','419민주묘지','4703','37.649593','127.013746'),('우이신설경전철','가오리','4704','37.641701','127.016792'),('우이신설경전철','삼양사거리','4707','37.621512','127.02048'),('우이신설경전철','솔샘','4708','37.62124','127.013528'),('우이신설경전철','북한산보국문','4709','37.612343','127.008009'),('우이신설경전철','정릉','4710','37.602798','127.01349'),('우이신설경전철','성신여대입구','4711','37.592624','127.016403'),('우이신설경전철','보문','4712','37.585286','127.019381'),('우이신설경전철','신설동','4713','37.575297','127.025087'),('우이신설경전철','화계','4705','37.634802','127.017519'),('우이신설경전철','삼양','4706','37.627165','127.018152'),('의정부경전철','동오','4608','37.745271','127.056947'),('의정부경전철','새말','4609','37.748885','127.06362'),('의정부경전철','경기도청북부청사','4610','37.75059','127.071495'),('의정부경전철','효자','4611','37.754025','127.076902'),('의정부경전철','회룡','4602','37.724846','127.046895'),('의정부경전철','범골','4603','37.728755','127.04353'),('의정부경전철','경전철의정부','4604','37.737202','127.043257'),('의정부경전철','의정부시청','4605','37.739256','127.034781'),('의정부경전철','흥선','4606','37.743302','127.037023'),('의정부경전철','의정부중앙','4607','37.743676','127.049565'),('의정부경전철','곤제','4612','37.750471','127.083715'),('의정부경전철','어룡','4613','37.742802','127.085035'),('의정부경전철','발곡','4601','37.727048','127.052803'),('의정부경전철','송산','4614','37.737279','127.087159'),('의정부경전철','탑석','4615','37.733579','127.088704'),('인천2호선','검단사거리','3203','37.60185','126.657108'),('인천2호선','인천대공원','3226','37.448769','126.752618'),('인천2호선','마전','3204','37.597566','126.666998'),('인천2호선','독정','3206','37.585212','126.675844'),('인천2호선','검바위','3208','37.561405','126.677566'),('인천2호선','서구청','3210','37.543742','126.676787'),('인천2호선','가정','3211','37.524649','126.675539'),('인천2호선','인천가좌','3215','37.4897','126.675208'),('인천2호선','가재울','3216','37.484192','126.683673'),('인천2호선','주안국가산단','3217','37.473703','126.68113'),('인천2호선','인천시청','3221','37.457405','126.702221'),('인천2호선','석천사거리','3222','37.456805','126.709986'),('인천2호선','모래내시장','3223','37.45583','126.719298'),('인천2호선','만수','3224','37.454911','126.732094'),('인천2호선','운연','3227','37.440127','126.75997'),('인천2호선','검암','3207','37.569104','126.673728'),('인천2호선','가정중앙시장','3212','37.517054','126.676672'),('인천2호선','석남','3213','37.506193','126.676203'),('인천2호선','서부여성회관','3214','37.506193','126.676203'),('인천2호선','주안','3218','37.464941','126.679923'),('인천2호선','시민공원','3219','37.458335','126.681192'),('인천2호선','석바위시장','3220','37.457611','126.692575'),('인천2호선','왕길','3202','37.59518','126.642696'),('인천2호선','완정','3205','37.592928','126.673203'),('인천2호선','남동구청','3225','37.448161','126.736939'),('인천2호선','아시아드경기장','3209','37.5517','126.677122'),('인천2호선','검단오류','3201','37.594877','126.627178'),('인천선','계양','3110','37.571462','126.735637'),('인천선','귤현','3111','37.566379','126.742654'),('인천선','박촌','3112','37.553703','126.745077'),('인천선','임학','3113','37.545059','126.738665'),('인천선','계산','3114','37.543238','126.728128'),('인천선','경인교대입구','3115','37.538157','126.722597'),('인천선','작전','3116','37.530415','126.722527'),('인천선','갈산','3117','37.517268','126.721514'),('인천선','부평구청','3118','37.508336','126.720548'),('인천선','부평시장','3119','37.498383','126.722244'),('인천선','부평','3120','37.489493','126.724805'),('인천선','동수','3121','37.485312','126.718247'),('인천선','부평삼거리','3122','37.477679','126.710208'),('인천선','인천시청','3124','37.457405','126.702221'),('인천선','예술회관','3125','37.449396','126.701012'),('인천선','인천터미널','3126','37.442383','126.699706'),('인천선','문학경기장','3127','37.434935','126.698579'),('인천선','선학','3128','37.426684','126.698863'),('인천선','신연수','3129','37.41804','126.693863'),('인천선','원인재','3130','37.412603','126.687389'),('인천선','동춘','3131','37.404737','126.681015'),('인천선','동막','3132','37.397878','126.674005'),('인천선','캠퍼스타운','3133','37.387855','126.661673'),('인천선','테크노파크','3134','37.382268','126.656365'),('인천선','지식정보단지','3135','37.378384','126.645168'),('인천선','인천대입구','3136','37.386007','126.639484'),('인천선','센트럴파크','3137','37.393054','126.634729'),('인천선','국제업무지구','3138','37.399907','126.630347'),('인천선','간석오거리','3123','37.467048','126.707938');
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-23  0:22:45
