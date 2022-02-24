package com.sparrows.geometry.geometry3;

// The 92 Johnson Solids

import com.sparrows.geometry.utils.GeometryUtils;

public class Johnson
{
	private static double R4_3_3 = GeometryUtils.trivalentVertexAngle(3, 3, 5);
	private static double R1_3_3 = GeometryUtils.trivalentVertexAngle(3, 3, 3);
	private static double U7_3_4 = Math.acos(-1 / Math.sqrt(3));
	private static double U10_4_4 = 3 * Math.PI / 4;
	private static double U27_4_5 = Math.acos(-Math.sqrt(2) / Math.sqrt(5 - Math.sqrt(5)));
	private static double U24_3_5 = Math.acos((Math.sqrt(5 - 2 * Math.sqrt(5)) - 2 * Math.sqrt(10 + 2 * Math.sqrt(5))) / (5 * Math.sqrt(3)));
	private static double U10_3_4 = GeometryUtils.trivalentVertexAngle(3, 4, 8);
	private static double R2_3_3 = GeometryUtils.trivalentVertexAngle(3, 3, 4);
	private static double R5_5_5 = GeometryUtils.trivalentVertexAngle(5, 5, 5);
	private static double R3_4_4 = Math.PI / 2;
	private static double U27_3_4 = GeometryUtils.trivalentVertexAngle(3, 4, 10);
	private static double J84_XB = 1.28916854644830996908267745816856738814;
	private static double J84_YB = 1.97898498017164637599616094723905191425;
	private static double J84_YC = 1.15673871675860822368475614743349356277;
	private static double J85_XB = 2.42641109173262666165204439214409140362;
	private static double J85_XC = 1.71573173691039433373702483221537987213;
	private static double J85_ZB = 0.9825229754983524694317412696077136282066;
	private static double J85_ZC = 1.72495106062707185971655584083764300066;
	private static double J86_XB = 1.70545388569283371081492313044538442703;
	private static double J86_YB = 1.04471385736727685418675116891276291612;
	private static double J86_YC = 1.91439980038178555878007598838708920242;
	private static double J86_ZC = 1.57885525332174329772195192048459890811;
	private static double J86_YD = 2.62659084852710857007385809347921618429;
	private static double J88_XB = 1.18926667126527706010484880458405740671;
	private static double J88_YB = 1.60799402505656327037636466971773595932;
	private static double J88_YC = 1.24378571613762520805874707646627723584;
	private static double J88_ZC = 2.56620467766253807983364462579308941622;
	private static double J88_YD = 3.05100274516968738300384165307913802827;
	private static double J88_ZD = 1.70948616497793025298935903002396210491;
	private static double J88_YE = 3.329672893820468831139933583782120464;
	private static double J89_XB = 1.43368963142691367434075969978110504334;
	private static double J89_YB = 1.95241217564140071898981918288456133114;
	private static double J89_YC = 1.24650402296707922228869168098004908688;
	private static double J89_ZC = 2.2025920840945558272545740964774878016;
	private static double J89_YD = 3.17465028297600531631628963584665830654;
	private static double J89_ZD = 1.67131814344473365646805970026946951958;
	private static double J89_YE = 3.62928823056996572733960007104726112145;
	private static double J90_XB = 1.53426222796692300383633549749761622683;
	private static double J90_YB = 1.2829806763291384770435141299672633776;
	private static double J90_YC = 1.55886993225992744772021916477598794449;
	private static double J90_ZC = 2.25296629415795906733401060113822183689;
	private static double J90_YD = 3.13477109199059750215248446173053933224;

	static public String[] JohnsonNames = new String[]
	{
		"square pyramid", "pentagonal pyramid", "triangular cupola", "square cupola", "pentagonal cupola",
		"pentagonal rotunda", "elongated triangular pyramid", "elongated square pyramid", "elongated pentagonal pyramid", "gyroelongated square pyramid",
		"gyroelongated pentagonal pyramid", "triangular dipyramid", "pentagonal dipyramid", "elongated triangular dipyramid", "elongated square dipyramid",
		"elongated pentagonal dipyramid", "gyroelongated square dipyramid", "elongated triangular cupola", "elongated square cupola", "elongated pentagonal cupola",
		"elongated pentagonal rotunda", "gyroelongated triangular cupola", "gyroelongated square cupola", "gyroelongated pentagonal cupola", "gyroelongated pentagonal rotunda",
		"gyrobifastigium", "triangular orthobicupola", "square orthobicupola", "square gyrobicupola", "pentagonal orthobicupola",
		"pentagonal gyrobicupola", "pentagonal orthocupolarotunda", "pentagonal gyrocupolarotunda", "pentagonal orthobirotunda", "elongated triangular orthobicupola",
		"elongated triangular gyrobicupola", "elongated square gyrobicupola", "elongated pentagonal orthobicupola", "elongated pentagonal gyrobicupola", "elongated pentagonal orthocupolarotunda",
		"elongated pentagonal gyrocupolarotunda", "elongated pentagonal orthobirotunda", "elongated pentagonal gyrobirotunda", "gyroelongated triangular bicupola", "gyroelongated square bicupola",
		"gyroelongated pentagonal bicupola", "gyroelongated pentagonal cupolarotunda", "gyroelongated pentagonal birotunda", "augmented triangular prism", "biaugmented triangular prism",
		"triaugmented triangular prism", "augmented pentagonal prism", "biaugmented pentagonal prism", "augmented hexagonal prism", "parabiaugmented hexagonal prism",
		"metabiaugmented hexagonal prism", "triaugmented hexagonal prism", "augmented dodecahedron", "parabiaugmented dodecahedron", "metabiaugmented dodecahedron",
		"triaugmented dodecahedron", "metabidiminished icosahedron", "tridiminished icosahedron", "augmented tridiminished icosahedron", "augmented truncated tetrahedron",
		"augmented truncated cube", "biaugmented truncated cube", "augmented truncated dodecahedron", "parabiaugmented truncated dodecahedron", "metabiaugmented truncated dodecahedron",
		"triaugmented truncated dodecahedron", "gyrate rhombicosidodecahedron", "parabigyrate rhombicosidodecahedron", "metabigyrate rhombicosidodecahedron", "trigyrate rhombicosidodecahedron",
		"diminished rhombicosidodecahedron", "paragyrate diminished rhombicosidodecahedron", "metagyrate diminished rhombicosidodecahedron", "bigyrate diminished rhombicosidodecahedron", "parabidiminished rhombicosidodecahedron",
		"metabidiminished rhombicosidodecahedron", "gyrate bidiminished rhombicosidodecahedron", "tridiminished rhombicosidodecahedron", "snub disphenoid", "snub square antiprism",
		"sphenocorona", "augmented sphenocorona", "sphenomegacorona", "hebesphenomegacorona", "disphenocingulum",
		"bilunabirotunda", "triangular hebesphenorotunda"
	};

	static public String[] JohnsonNamesJapanese = new String[]
	{
		"正四角錐", "正五角錐", "正三角台塔", "正四角台塔", "正五角台塔",
		"正五角丸塔", "正三角錐柱", "正四角錐柱", "正五角錐柱", "正四角錐反柱",
		"正五角錐反柱", "双三角錐", "双五角錐", "双三角錐柱", "双四角錐柱",
		"双五角錐柱", "双四角錐反柱", "正三角台塔柱", "正四角台塔柱", "正五角台塔柱",
		"正五角丸塔柱", "正三角台塔反柱", "正四角台塔反柱", "正五角台塔反柱", "正五角丸塔反柱",
		"異相双三角柱", "同相双三角台塔", "同相双四角台塔", "異相双四角台塔", "同相双五角台塔",
		"異相双五角台塔", "同相五角台塔丸塔", "異相五角台塔丸塔", "同相双五角丸塔", "同相双三角台塔柱",
		"異相双三角台塔柱", "異相双四角台塔柱", "同相双五角台塔柱", "異相双五角台塔柱", "同相五角台塔丸塔柱",
		"異相五角台塔丸塔柱", "同相双五角丸塔柱", "異相双五角丸塔柱", "双三角台塔反柱","双四角台塔反柱",
		"双五角台塔反柱", "五角台塔丸塔反柱", "双五角丸塔反柱", "側錐三角柱", "二側錐三角柱",
		"三側錐三角柱", "側錐五角柱", "二側錐五角柱", "側錐六角柱", "双側錐六角柱",
		"二側錐六角柱", "三側錐六角柱", "側錐十二面体", "双側錐十二面体", "二側錐十二面体",
		"三側錐十二面体", "二側錐欠損二十面体", "三側錐欠損二十面体", "側錐三側錐欠損二十面体", "側台塔切頂四面体",
		"側台塔切頂立方体", "双側台塔切頂立方体", "側台塔切頂十二面体", "双側台塔切頂十二面体", "二側台塔切頂十二面体",
		"三側台塔切頂十二面体", "側台塔回転斜方二十・十二面体", "双側台塔回転斜方二十・十二面体", "二側台塔回転斜方二十・十二面体","三側台塔回転斜方二十・十二面体",
		"側台塔欠損斜方二十・十二面体", "双側台塔回転欠損斜方二十・十二面体", "二側台塔回転欠損斜方二十・十二面体", "二側台塔回転側台塔欠損斜方二十・十二面体", "双側台塔欠損斜方二十・十二面体",
		"二側台塔欠損斜方二十・十二面体", "側台塔回転二側台塔欠損斜方二十・十二面体", "三側台塔欠損斜方二十・十二面体", "変形双五角錐", "変形四角反柱",
		"球形屋根", "側錐球形屋根", "長球形屋根", "広底長球形屋根", "五角錐球形屋根",
		"双三日月双丸塔", "三角広底球形屋根丸塔"
	};

	private static double[][] JohnsonData = new double[][] {
		new double[] // J1 - square pyramid
		{
			5,

			// start with the square base
			4, 1,

			// a triangle next to each edge of the squares
			0, 0, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,3),
			0, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,3),
			0, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,3),
			0, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,3)
		},

		new double[] // J2 - pentagonal pyramid
		{
			6,

			// start with the pentagonal base
			5, 1,

			// a triangle next to each edge of the pentagon
			0, 0, 3, 1, GeometryUtils.trivalentVertexAngle(3,5,3),
			0, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,5,3),
			0, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,5,3),
			0, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,5,3),
			0, 4, 3, 1, GeometryUtils.trivalentVertexAngle(3,5,3)
		},

		new double[] // J3 - triangular cupola
		{
			8,

			// start with the hexagonal base
			6, 1,

			// ring of squares and triangles
			0, 0, 4, 1, GeometryUtils.trivalentVertexAngle(4,6,3),
			0, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,6,4),
			0, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,6,3),
			0, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,6,4),
			0, 4, 4, 1, GeometryUtils.trivalentVertexAngle(4,6,3),
			0, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,6,4),

			// triangle opposite the hexagon
			1, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,6)
		},

		new double[]// J4 - square cupola
		{
			10,

			// start with octagonal base
			8, 1,

			// ring of squares and triangles
			0, 0, 4, 1, GeometryUtils.trivalentVertexAngle(4,8,3),
			0, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,4),
			0, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,8,3),
			0, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,4),
			0, 4, 4, 1, GeometryUtils.trivalentVertexAngle(4,8,3),
			0, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,4),
			0, 6, 4, 1, GeometryUtils.trivalentVertexAngle(4,8,3),
			0, 7, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,4),

			// square opposite the octagon
			1, 2, 4, 1, Math.PI-GeometryUtils.trivalentVertexAngle(4,8,3)/*U10_4_4*/
		},

		new double[] // J5 - pentagonal cupola
		{
			12,

			// decagonal base
			10, 1,

			// ring of squares and triangles
			0, 0, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3),
			0, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4),
			0, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3),
			0, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4),
			0, 4, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3),
			0, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4),
			0, 6, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3),
			0, 7, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4),
			0, 8, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3),
			0, 9, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4),

			// pentagon opposite the base
			1, 2, 5, 1, Math.PI-GeometryUtils.trivalentVertexAngle(4,10,3)
		},

		new double[] // J6 - pentagonal rotunda
		{
			17,

			// decagonal base
			10, 1,

			// ring of pentagons and triangles
			0, 0, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3),
			0, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5),
			0, 2, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3),
			0, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5),
			0, 4, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3),
			0, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5),
			0, 6, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3),
			0, 7, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5),
			0, 8, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3),
			0, 9, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5),

			// ring of triangles
			1, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,5,10),
			3, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,5,10),
			5, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,5,10),
			7, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,5,10),
			9, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,5,10),

			// pentagon opposite the decagon
			11, 2, 5, 1, GeometryUtils.trivalentVertexAngle(3,5,10)
		},

		new double[] // J7 - elongated triangular pyramid
		 {
			 7,

			 // base triangle
			 3, 1,

			 // elongating squares
			 0, 0, 4, 1, Math.PI/2,
			 0, 1, 4, 1, Math.PI/2,
			 0, 2, 4, 1, Math.PI/2,

			 // pyramidal triangles
			 1, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,3,3)+Math.PI/2,
			 2, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,3,3)+Math.PI/2,
			 3, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,3,3)+Math.PI/2,
		 },

		 new double[] // J8 - elongated square pyramid
		 {
			 9,

			 // base square
			 4, 1,

			 // elongating squares
			 0, 0, 4, 1, Math.PI/2,
			 0, 1, 4, 1, Math.PI/2,
			 0, 2, 4, 1, Math.PI/2,
			 0, 3, 4, 1, Math.PI/2,

			 // pyramidal triangles
			 1, 2, 3, 1, Math.PI/2+GeometryUtils.trivalentVertexAngle(3,4,3),
			 2, 2, 3, 1, Math.PI/2+GeometryUtils.trivalentVertexAngle(3,4,3),
			 3, 2, 3, 1, Math.PI/2+GeometryUtils.trivalentVertexAngle(3,4,3),
			 4, 2, 3, 1, Math.PI/2+GeometryUtils.trivalentVertexAngle(3,4,3)
		 },

		 new double[] // J9 - elongated pentagonal pyramid
		 {
			 11,

			 // base pentagon
			 5, 1,

			 // elongating squares
			 0, 0, 4, 1, Math.PI/2,
			 0, 1, 4, 1, Math.PI/2,
			 0, 2, 4, 1, Math.PI/2,
			 0, 3, 4, 1, Math.PI/2,
			 0, 4, 4, 1, Math.PI/2,

			 // pyramidal triangles
			 1, 2, 3, 1, Math.PI/2+GeometryUtils.trivalentVertexAngle(3,5,3),
			 2, 2, 3, 1, Math.PI/2+GeometryUtils.trivalentVertexAngle(3,5,3),
			 3, 2, 3, 1, Math.PI/2+GeometryUtils.trivalentVertexAngle(3,5,3),
			 4, 2, 3, 1, Math.PI/2+GeometryUtils.trivalentVertexAngle(3,5,3),
			 5, 2, 3, 1, Math.PI/2+GeometryUtils.trivalentVertexAngle(3,5,3)
		 },

		 new double[] // J10 - gyroelongated square pyramid
		 {
			 13,

			 // square base
			 4, 1,

			 // bottom ring of triangles
			 0, 0, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(4,1),
			 0, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(4,1),
			 0, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(4,1),
			 0, 3, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(4,1),

			 // top ring of triangles
			 1, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(4,1),
			 2, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(4,1),
			 3, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(4,1),
			 4, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(4,1),

			 // pyramidal triangles
			 5, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(4,1)+GeometryUtils.trivalentVertexAngle(3,4,3),
			 6, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(4,1)+GeometryUtils.trivalentVertexAngle(3,4,3),
			 7, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(4,1)+GeometryUtils.trivalentVertexAngle(3,4,3),
			 8, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(4,1)+GeometryUtils.trivalentVertexAngle(3,4,3)
		 },

		new double[] // J11 - gyroelongated pentagonal pyramid (diminished icosahedron)
		{
			16,

			// pentagonal base
			5, 1,

			// bottom ring of triangles
			0, 0, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(5,1),
			0, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(5,1),
			0, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(5,1),
			0, 3, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(5,1),
			0, 4, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(5,1),

			// top ring of triangles
			1, 1, 3, 1, R4_3_3,
			2, 1, 3, 1, R4_3_3,
			3, 1, 3, 1, R4_3_3,
			4, 1, 3, 1, R4_3_3,
			5, 1, 3, 1, R4_3_3,

			// pyramidal triangles
			6, 2, 3, 1, R4_3_3,
			7, 2, 3, 1, R4_3_3,
			8, 2, 3, 1, R4_3_3,
			9, 2, 3, 1, R4_3_3,
			10, 2, 3, 1, R4_3_3
		},

		new double[] // J12 - triangular dipyramid
		{
			6,

			// three triangles forming pyramid
			3, 1,
			0, 0, 3, 1, R1_3_3,
			1, 1, 3, 1, R1_3_3,

			// pyramid opposite
			0, 1, 3, 1, 2*R1_3_3,
			1, 2, 3, 1, 2*R1_3_3,
			2, 2, 3, 1, 2*R1_3_3
		},

		new double[] // J13 - pentagonal dipyramid
		{
			10,

			// five triangles forming pentagonal pyramid
			3, 1,
			0, 1, 3, 1, R4_3_3,
			1, 1, 3, 1, R4_3_3,
			2, 1, 3, 1, R4_3_3,
			3, 1, 3, 1, R4_3_3,

			// pyramid opposite
			0, 2, 3, 1, 2*GeometryUtils.trivalentVertexAngle(3,5,3),
			1, 2, 3, 1, 2*GeometryUtils.trivalentVertexAngle(3,5,3),
			2, 2, 3, 1, 2*GeometryUtils.trivalentVertexAngle(3,5,3),
			3, 2, 3, 1, 2*GeometryUtils.trivalentVertexAngle(3,5,3),
			4, 2, 3, 1, 2*GeometryUtils.trivalentVertexAngle(3,5,3),
		},

		new double[]// J14 - elongated triangular dipyramid
		{
			9,

			// ring of squares
			4, 1,
			0, 2, 4, 1, Math.PI/3,
			1, 2, 4, 1, Math.PI/3,

			// pyramid one end
			0, 1, 3, 1, Math.PI/2+R1_3_3,
			1, 1, 3, 1, Math.PI/2+R1_3_3,
			2, 1, 3, 1, Math.PI/2+R1_3_3,

			// pyramid the other end
			0, 3, 3, 1, Math.PI/2+R1_3_3,
			1, 3, 3, 1, Math.PI/2+R1_3_3,
			2, 3, 3, 1, Math.PI/2+R1_3_3
		},

		new double[] // J15 - elongated square dipyramid
		{
			12,
			4, 1,

			// ring of squares
			0, 2, 4, 1, Math.PI/2,
			1, 2, 4, 1, Math.PI/2,
			2, 2, 4, 1, Math.PI/2,

			// pyramid one end
			0, 1, 3, 1, Math.PI/2+GeometryUtils.trivalentVertexAngle(3,4,3),
			1, 1, 3, 1, Math.PI/2+GeometryUtils.trivalentVertexAngle(3,4,3),
			2, 1, 3, 1, Math.PI/2+GeometryUtils.trivalentVertexAngle(3,4,3),
			3, 1, 3, 1, Math.PI/2+GeometryUtils.trivalentVertexAngle(3,4,3),

			// pyramid the other end
			0, 3, 3, 1, Math.PI/2+GeometryUtils.trivalentVertexAngle(3,4,3),
			1, 3, 3, 1, Math.PI/2+GeometryUtils.trivalentVertexAngle(3,4,3),
			2, 3, 3, 1, Math.PI/2+GeometryUtils.trivalentVertexAngle(3,4,3),
			3, 3, 3, 1, Math.PI/2+GeometryUtils.trivalentVertexAngle(3,4,3),
		},

		new double[] // J16 - elongated pentagonal dipyramid
		{
			15,
			4, 1,

			// ring of squares
			0, 2, 4, 1, 3*Math.PI/5,
			1, 2, 4, 1, 3*Math.PI/5,
			2, 2, 4, 1, 3*Math.PI/5,
			3, 2, 4, 1, 3*Math.PI/5,

			// pyramid one end
			0, 1, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,5,3),
			1, 1, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,5,3),
			2, 1, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,5,3),
			3, 1, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,5,3),
			4, 1, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,5,3),

			// pyramid the other end
			0, 3, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,5,3),
			1, 3, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,5,3),
			2, 3, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,5,3),
			3, 3, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,5,3),
			4, 3, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,5,3)
		},

		new double[] // J17 - gyroelongated square dipyramid
		{
			16,

			// ring of triangles
			3, 1,
			0, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(4,1),
			1, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(4,1),
			2, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(4,1),
			3, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(4,1),
			4, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(4,1),
			5, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(4,1),
			6, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(4,1),

			// square pyramid at one end
			0, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(4,1) + GeometryUtils.trivalentVertexAngle(3,4,3),
			2, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(4,1) + GeometryUtils.trivalentVertexAngle(3,4,3),
			4, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(4,1) + GeometryUtils.trivalentVertexAngle(3,4,3),
			6, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(4,1) + GeometryUtils.trivalentVertexAngle(3,4,3),

			// square pyramid at other end
			1, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(4,1) + GeometryUtils.trivalentVertexAngle(3,4,3),
			3, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(4,1) + GeometryUtils.trivalentVertexAngle(3,4,3),
			5, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(4,1) + GeometryUtils.trivalentVertexAngle(3,4,3),
			7, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(4,1) + GeometryUtils.trivalentVertexAngle(3,4,3)
		},

		new double[] // J18 - elongated triangula cupola
		{
			14,

			// base hexagon
			6, 1,

			// ring of squares
			0, 0, 4, 1, Math.PI/2,
			0, 1, 4, 1, Math.PI/2,
			0, 2, 4, 1, Math.PI/2,
			0, 3, 4, 1, Math.PI/2,
			0, 4, 4, 1, Math.PI/2,
			0, 5, 4, 1, Math.PI/2,

			// ring of squares and triangles
			1, 2, 4, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(4,6,3),
			2, 2, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,6,4),
			3, 2, 4, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(4,6,3),
			4, 2, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,6,4),
			5, 2, 4, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(4,6,3),
			6, 2, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,6,4),

			// triangle opposite the base hexagon
			7, 2, 3, 1, U7_3_4
		},

		new double[] // J19 - elongated square cupola
		{
			18,

			// base octagon
			8, 1,

			// ring of squares
			0, 0, 4, 1, Math.PI/2,
			0, 1, 4, 1, Math.PI/2,
			0, 2, 4, 1, Math.PI/2,
			0, 3, 4, 1, Math.PI/2,
			0, 4, 4, 1, Math.PI/2,
			0, 5, 4, 1, Math.PI/2,
			0, 6, 4, 1, Math.PI/2,
			0, 7, 4, 1, Math.PI/2,

			// ring of squares and triangles
			1, 2, 4, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(4,8,3),
			2, 2, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,8,4),
			3, 2, 4, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(4,8,3),
			4, 2, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,8,4),
			5, 2, 4, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(4,8,3),
			6, 2, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,8,4),
			7, 2, 4, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(4,8,3),
			8, 2, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,8,4),

			// square opposite the base octagon
			9, 2, 4, 1, U10_4_4
		},

		new double[] // J20 - elongated pentagonal cupola
		{
			22,

			// base decagon
			10, 1,

			// ring of squares
			0, 0, 4, 1, Math.PI/2,
			0, 1, 4, 1, Math.PI/2,
			0, 2, 4, 1, Math.PI/2,
			0, 3, 4, 1, Math.PI/2,
			0, 4, 4, 1, Math.PI/2,
			0, 5, 4, 1, Math.PI/2,
			0, 6, 4, 1, Math.PI/2,
			0, 7, 4, 1, Math.PI/2,
			0, 8, 4, 1, Math.PI/2,
			0, 9, 4, 1, Math.PI/2,

			// ring of squares and triangles
			1, 2, 4, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(4,10,3),
			2, 2, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,10,4),
			3, 2, 4, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(4,10,3),
			4, 2, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,10,4),
			5, 2, 4, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(4,10,3),
			6, 2, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,10,4),
			7, 2, 4, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(4,10,3),
			8, 2, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,10,4),
			9, 2, 4, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(4,10,3),
			10, 2, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,10,4),

			// pentagon opposite the base decagon
			11, 2, 5, 1, U27_4_5
		},

		new double[] // J21 - elongated pentagonal rotunda
		{
			27,

			// decagonal base
			10, 1,

			// elongating squares
			0, 0, 4, 1, Math.PI/2,
			0, 1, 4, 1, Math.PI/2,
			0, 2, 4, 1, Math.PI/2,
			0, 3, 4, 1, Math.PI/2,
			0, 4, 4, 1, Math.PI/2,
			0, 5, 4, 1, Math.PI/2,
			0, 6, 4, 1, Math.PI/2,
			0, 7, 4, 1, Math.PI/2,
			0, 8, 4, 1, Math.PI/2,
			0, 9, 4, 1, Math.PI/2,

			// ring of pentagons and triangles
			1, 2, 5, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(5,10,3),
			2, 2, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,10,5),
			3, 2, 5, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(5,10,3),
			4, 2, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,10,5),
			5, 2, 5, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(5,10,3),
			6, 2, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,10,5),
			7, 2, 5, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(5,10,3),
			8, 2, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,10,5),
			9, 2, 5, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(5,10,3),
			10, 2, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,10,5),

			// ring of triangles
			11, 2, 3, 1, U24_3_5,
			13, 2, 3, 1, U24_3_5,
			15, 2, 3, 1, U24_3_5,
			17, 2, 3, 1, U24_3_5,
			19, 2, 3, 1, U24_3_5,

			// pentagon opposite the decagon
			25, 2, 5, 1, U24_3_5
		},

		new double[] // J22 - gyroelongated triangular cupola
		{
			20,

			// hexagonal base
			6, 1,

			// bottom ring of triangles
			0, 0, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(6,1),
			0, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(6,1),
			0, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(6,1),
			0, 3, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(6,1),
			0, 4, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(6,1),
			0, 5, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(6,1),

			// second ring of triangles
			1, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(6,1),
			2, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(6,1),
			3, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(6,1),
			4, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(6,1),
			5, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(6,1),
			6, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(6,1),

			// ring of squares and triangles
			7, 2, 4, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(6,1) + GeometryUtils.trivalentVertexAngle(4,6,3),
			8, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(6,1) + GeometryUtils.trivalentVertexAngle(3,6,4),
			9, 2, 4, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(6,1) + GeometryUtils.trivalentVertexAngle(4,6,3),
			10, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(6,1) + GeometryUtils.trivalentVertexAngle(3,6,4),
			11, 2, 4, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(6,1) + GeometryUtils.trivalentVertexAngle(4,6,3),
			12, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(6,1) + GeometryUtils.trivalentVertexAngle(3,6,4),

			// triangle opposite the base hexagon
			13, 2, 3, 1, U7_3_4
		},

		new double[] // J23 - gyroelongated square cupola
		{
			26,

			// base octagon
			8, 1,

			// ring of triangles
			0, 0, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1),
			0, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1),
			0, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1),
			0, 3, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1),
			0, 4, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1),
			0, 5, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1),
			0, 6, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1),
			0, 7, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1),

			// second ring of triangles
			1, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(8,1),
			2, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(8,1),
			3, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(8,1),
			4, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(8,1),
			5, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(8,1),
			6, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(8,1),
			7, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(8,1),
			8, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(8,1),

			// ring of squares and triangles
			9, 2, 4, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1) + GeometryUtils.trivalentVertexAngle(4,8,3),
			10, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1) + GeometryUtils.trivalentVertexAngle(3,8,4),
			11, 2, 4, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1) + GeometryUtils.trivalentVertexAngle(4,8,3),
			12, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1) + GeometryUtils.trivalentVertexAngle(3,8,4),
			13, 2, 4, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1) + GeometryUtils.trivalentVertexAngle(4,8,3),
			14, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1) + GeometryUtils.trivalentVertexAngle(3,8,4),
			15, 2, 4, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1) + GeometryUtils.trivalentVertexAngle(4,8,3),
			16, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1) + GeometryUtils.trivalentVertexAngle(3,8,4),

			// square opposite the octagon
			17, 2, 4, 1, U10_4_4
		},

		new double[] // J24 - gyroelongated pentagonal cupola
		{
			32,

			// base decagon
			10, 1,

			// ring of triangles
			0, 0, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			0, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			0, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			0, 3, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			0, 4, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			0, 5, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			0, 6, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			0, 7, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			0, 8, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			0, 9, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),

			// second ring of triangles
			1, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			2, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			3, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			4, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			5, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			6, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			7, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			8, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			9, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			10, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),

			// ring of squares and triangles
			11, 2, 4, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(4,10,3),
			12, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(3,10,4),
			13, 2, 4, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(4,10,3),
			14, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(3,10,4),
			15, 2, 4, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(4,10,3),
			16, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(3,10,4),
			17, 2, 4, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(4,10,3),
			18, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(3,10,4),
			19, 2, 4, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(4,10,3),
			20, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(3,10,4),

			// pentagonal opposite the base
			21, 2, 5, 1, U27_4_5
		},

		new double[] // J25 - gyroelongated pentagonal rotunda
		{
			37,

			// base decagon
			10, 1,

			// ring of triangles
			0, 0, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			0, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			0, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			0, 3, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			0, 4, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			0, 5, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			0, 6, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			0, 7, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			0, 8, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			0, 9, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),

			// second ring of triangles
			1, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			2, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			3, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			4, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			5, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			6, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			7, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			8, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			9, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			10, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),

			// ring of pentagons and triangles
			11, 2, 5, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(5,10,3),
			12, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(3,10,5),
			13, 2, 5, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(5,10,3),
			14, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(3,10,5),
			15, 2, 5, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(5,10,3),
			16, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(3,10,5),
			17, 2, 5, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(5,10,3),
			18, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(3,10,5),
			19, 2, 5, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(5,10,3),
			20, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(3,10,5),

			// ring of triangles
			21, 2, 3, 1, U24_3_5,
			23, 2, 3, 1, U24_3_5,
			25, 2, 3, 1, U24_3_5,
			27, 2, 3, 1, U24_3_5,
			29, 2, 3, 1, U24_3_5,

			// pentagon opposite the decagon
			31, 2, 5, 1, U24_3_5
		},

		new double[] // J26 - gyrobifastigium
		{
			8,
			4, 1,
			0, 0, 4, 1, Math.PI/3,
			0, 1, 3, 1, Math.PI/2,
			0, 2, 3, 1, 5*Math.PI/6,
			0, 3, 3, 1, Math.PI/2,
			1, 2, 3, 1, 5*Math.PI/6,
			2, 2, 4, 1, 5*Math.PI/6,
			4, 1, 4, 1, 5*Math.PI/6
		},

		new double[] // J27 - triangular orthobicupola
		{
			14,

			// triangle in centre of one cupola
			3, 1,

			// three surrounding squares
			0, 0, 4, 1, U7_3_4,
			0, 1, 4, 1, U7_3_4,
			0, 2, 4, 1, U7_3_4,

			// triangles between the squares
			1, 1, 3, 1, U7_3_4,
			2, 1, 3, 1, U7_3_4,
			3, 1, 3, 1, U7_3_4,

			// squares of the second cupola
			1, 2, 4, 1, 2*GeometryUtils.trivalentVertexAngle(4,6,3),
			2, 2, 4, 1, 2*GeometryUtils.trivalentVertexAngle(4,6,3),
			3, 2, 4, 1, 2*GeometryUtils.trivalentVertexAngle(4,6,3),

			// triangles between the squares
			7, 1, 3, 1, U7_3_4,
			8, 1, 3, 1, U7_3_4,
			9, 1, 3, 1, U7_3_4,

			// top triangle of the second cupola
			7, 2, 3, 1, U7_3_4
		},

		new double[] // J28 - square orthobicupola
		{
			18,

			// square in the centre of the first cupola
			4, 1,

			// surrounding squares
			0, 0, 4, 1, U10_4_4,
			0, 1, 4, 1, U10_4_4,
			0, 2, 4, 1, U10_4_4,
			0, 3, 4, 1, U10_4_4,

			// triangles between the squares
			1, 1, 3, 1, U10_3_4,
			2, 1, 3, 1, U10_3_4,
			3, 1, 3, 1, U10_3_4,
			4, 1, 3, 1, U10_3_4,

			// squares of the second cupola opposite squares of first cupola
			1, 2, 4, 1, 2 * GeometryUtils.trivalentVertexAngle(4,8,3),
			2, 2, 4, 1, 2 * GeometryUtils.trivalentVertexAngle(4,8,3),
			3, 2, 4, 1, 2 * GeometryUtils.trivalentVertexAngle(4,8,3),
			4, 2, 4, 1, 2 * GeometryUtils.trivalentVertexAngle(4,8,3),

			// triangles of the second cupola opposite triangles of the first cupola
			5, 2, 3, 1, 2 * GeometryUtils.trivalentVertexAngle(3,8,4),
			6, 2, 3, 1, 2 * GeometryUtils.trivalentVertexAngle(3,8,4),
			7, 2, 3, 1, 2 * GeometryUtils.trivalentVertexAngle(3,8,4),
			8, 2, 3, 1, 2 * GeometryUtils.trivalentVertexAngle(3,8,4),

			// centre square of the second cupola
			9, 2, 4, 1, U10_4_4
		},

		new double[] // J29 - square gyrobicupola
		{
			18,

			// central square of one cupola
			4, 1,

			// surrounding squares
			0, 0, 4, 1, U10_4_4,
			0, 1, 4, 1, U10_4_4,
			0, 2, 4, 1, U10_4_4,
			0, 3, 4, 1, U10_4_4,

			// triangles between squares
			1, 1, 3, 1, U10_3_4,
			2, 1, 3, 1, U10_3_4,
			3, 1, 3, 1, U10_3_4,
			4, 1, 3, 1, U10_3_4,

			// squares of second cupola opposite triangles of first cupola
			5, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,8,4) + GeometryUtils.trivalentVertexAngle(4,8,3),
			6, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,8,4) + GeometryUtils.trivalentVertexAngle(4,8,3),
			7, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,8,4) + GeometryUtils.trivalentVertexAngle(4,8,3),
			8, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,8,4) + GeometryUtils.trivalentVertexAngle(4,8,3),

			// triangles of second cupola opposite squares of first cupola
			1, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,8,3) + GeometryUtils.trivalentVertexAngle(3,8,4),
			2, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,8,3) + GeometryUtils.trivalentVertexAngle(3,8,4),
			3, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,8,3) + GeometryUtils.trivalentVertexAngle(3,8,4),
			4, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,8,3) + GeometryUtils.trivalentVertexAngle(3,8,4),

			// central square of second cupola
			9, 2, 4, 1, U10_4_4
		},

		new double[] // J30 - pentagonal orthobicupola
		{
			22,

			// central pentagon of first cupola
			5, 1,

			// surrounding squares
			0, 0, 4, 1, U27_4_5,
			0, 1, 4, 1, U27_4_5,
			0, 2, 4, 1, U27_4_5,
			0, 3, 4, 1, U27_4_5,
			0, 4, 4, 1, U27_4_5,

			// triangles between squares
			1, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			2, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			3, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			4, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			5, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),

			// squares of second cupola opposite squares of first
			1, 2, 4, 1, 2 * GeometryUtils.trivalentVertexAngle(4,10,3),
			2, 2, 4, 1, 2 * GeometryUtils.trivalentVertexAngle(4,10,3),
			3, 2, 4, 1, 2 * GeometryUtils.trivalentVertexAngle(4,10,3),
			4, 2, 4, 1, 2 * GeometryUtils.trivalentVertexAngle(4,10,3),
			5, 2, 4, 1, 2 * GeometryUtils.trivalentVertexAngle(4,10,3),

			// triangles of second cupola
			11, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			12, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			13, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			14, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			15, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),

			// central pentagon of second cupola
			11, 2, 5, 1, U27_4_5
		},

		new double[] // J31 - pentagonal gyrobicupola
		{
			22,

			// central pentagon of first cupola
			5, 1,

			// surrounding squares
			0, 0, 4, 1, U27_4_5,
			0, 1, 4, 1, U27_4_5,
			0, 2, 4, 1, U27_4_5,
			0, 3, 4, 1, U27_4_5,
			0, 4, 4, 1, U27_4_5,

			// triangles between squares
			1, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			2, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			3, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			4, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			5, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),

			// squares of second cupola opposite triangles of first
			6, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.trivalentVertexAngle(4,10,3),
			7, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.trivalentVertexAngle(4,10,3),
			8, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.trivalentVertexAngle(4,10,3),
			9, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.trivalentVertexAngle(4,10,3),
			10, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.trivalentVertexAngle(4,10,3),

			// triangles of second cupola
			11, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			12, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			13, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			14, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			15, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),

			// central pentagon of second cupola
			11, 2, 5, 1, U27_4_5
		},

		new double[] // J32 - pentagonal orthocupolarontunda
		{
			27,

			// central pentagon of cupola
			5, 1,

			// squares surrounding pentagon
			0, 0, 4, 1, U27_4_5,
			0, 1, 4, 1, U27_4_5,
			0, 2, 4, 1, U27_4_5,
			0, 3, 4, 1, U27_4_5,
			0, 4, 4, 1, U27_4_5,

			// triangles between squares
			1, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			2, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			3, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			4, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			5, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),

			// outer pentagons of rotunda opposite triangles of cupola
			6, 2, 5, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.trivalentVertexAngle(5,10,3),
			7, 2, 5, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.trivalentVertexAngle(5,10,3),
			8, 2, 5, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.trivalentVertexAngle(5,10,3),
			9, 2, 5, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.trivalentVertexAngle(5,10,3),
			10, 2, 5, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.trivalentVertexAngle(5,10,3),

			// outer triangles of rotunda opposite squares of cupola
			1, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.trivalentVertexAngle(3,10,5),
			2, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.trivalentVertexAngle(3,10,5),
			3, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.trivalentVertexAngle(3,10,5),
			4, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.trivalentVertexAngle(3,10,5),
			5, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.trivalentVertexAngle(3,10,5),

			// inner triangles of rotunda
			11, 2, 3, 1, U24_3_5,
			12, 2, 3, 1, U24_3_5,
			13, 2, 3, 1, U24_3_5,
			14, 2, 3, 1, U24_3_5,
			15, 2, 3, 1, U24_3_5,

			// central pentagon of rotunda
			25, 2, 5, 1, U24_3_5
		},

		new double[] // J33 - pentagonal gyrocupolarontunda
		{
			27,

			// central pentagon of cupola
			5, 1,

			// squares surrounding pentagon
			0, 0, 4, 1, U27_4_5,
			0, 1, 4, 1, U27_4_5,
			0, 2, 4, 1, U27_4_5,
			0, 3, 4, 1, U27_4_5,
			0, 4, 4, 1, U27_4_5,

			// triangles between squares
			1, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			2, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			3, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			4, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			5, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),

			// outer pentagons of rotunda opposite squares of cupola
			1, 2, 5, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.trivalentVertexAngle(5,10,3),
			2, 2, 5, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.trivalentVertexAngle(5,10,3),
			3, 2, 5, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.trivalentVertexAngle(5,10,3),
			4, 2, 5, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.trivalentVertexAngle(5,10,3),
			5, 2, 5, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.trivalentVertexAngle(5,10,3),

			// outer triangles of rotunda opposite triangles of cupola
			6, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.trivalentVertexAngle(3,10,5),
			7, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.trivalentVertexAngle(3,10,5),
			8, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.trivalentVertexAngle(3,10,5),
			9, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.trivalentVertexAngle(3,10,5),
			10, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.trivalentVertexAngle(3,10,5),

			// inner triangles of rotunda
			11, 2, 3, 1, U24_3_5,
			12, 2, 3, 1, U24_3_5,
			13, 2, 3, 1, U24_3_5,
			14, 2, 3, 1, U24_3_5,
			15, 2, 3, 1, U24_3_5,

			// central pentagon of rotunda
			25, 2, 5, 1, U24_3_5
		},

		new double[] // J34 - pentagonal orthobirontunda
		{
			32,

			// central pentagon of one rotunda
			5, 1,

			// inner ring of triangles
			0, 0, 3, 1, U24_3_5,
			0, 1, 3, 1, U24_3_5,
			0, 2, 3, 1, U24_3_5,
			0, 3, 3, 1, U24_3_5,
			0, 4, 3, 1, U24_3_5,

			// outer ring of pentagons
			1, 1, 5, 1, U24_3_5,
			2, 1, 5, 1, U24_3_5,
			3, 1, 5, 1, U24_3_5,
			4, 1, 5, 1, U24_3_5,
			5, 1, 5, 1, U24_3_5,

			// outer ring of triangles amongst pentagons
			6, 2, 3, 1, U24_3_5,
			7, 2, 3, 1, U24_3_5,
			8, 2, 3, 1, U24_3_5,
			9, 2, 3, 1, U24_3_5,
			10, 2, 3, 1, U24_3_5,

			// pentagons of second rotunda opposite pentagon of first rotunda
			6, 3, 5, 1, 2 * GeometryUtils.trivalentVertexAngle(5,10,3),
			7, 3, 5, 1, 2 * GeometryUtils.trivalentVertexAngle(5,10,3),
			8, 3, 5, 1, 2 * GeometryUtils.trivalentVertexAngle(5,10,3),
			9, 3, 5, 1, 2 * GeometryUtils.trivalentVertexAngle(5,10,3),
			10, 3, 5, 1, 2 * GeometryUtils.trivalentVertexAngle(5,10,3),

			// outer ring of triangles amongst pentagons
			16, 1, 3, 1, U24_3_5,
			17, 1, 3, 1, U24_3_5,
			18, 1, 3, 1, U24_3_5,
			19, 1, 3, 1, U24_3_5,
			20, 1, 3, 1, U24_3_5,

			// inner ring of triangles
			16, 2, 3, 1, U24_3_5,
			17, 2, 3, 1, U24_3_5,
			18, 2, 3, 1, U24_3_5,
			19, 2, 3, 1, U24_3_5,
			20, 2, 3, 1, U24_3_5,

			// central pentagon of second rotunda
			30, 2, 5, 1, U24_3_5
		},

		new double[] // J35 - elongated triangular orthobicupola
		{
			20,

			// central triangle of first cupola
			3, 1,

			// surrounding squares
			0, 0, 4, 1, U7_3_4,
			0, 1, 4, 1, U7_3_4,
			0, 2, 4, 1, U7_3_4,

			// triangles between squares
			1, 1, 3, 1, U7_3_4,
			2, 1, 3, 1, U7_3_4,
			3, 1, 3, 1, U7_3_4,

			// elongating squares adjacent to cupola squares
			1, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,6,3) + Math.PI/2,
			2, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,6,3) + Math.PI/2,
			3, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,6,3) + Math.PI/2,

			// elongating squares adjacent to cupola triangles
			4, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,6,4) + Math.PI/2,
			5, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,6,4) + Math.PI/2,
			6, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,6,4) + Math.PI/2,

			// squares of second cupola
			7, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,6,3) + Math.PI/2,
			8, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,6,3) + Math.PI/2,
			9, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,6,3) + Math.PI/2,

			// triangles between squares
			13, 1, 3, 1, U7_3_4,
			14, 1, 3, 1, U7_3_4,
			15, 1, 3, 1, U7_3_4,

			// central triangle of second cupola
			13, 2, 3, 1, U7_3_4
		},

		new double[] // J36 - elongated triangular gyrobicupola
		{
			20,

			// central triangle of one cupola
			3, 1,

			// surrounding squares
			0, 0, 4, 1, U7_3_4,
			0, 1, 4, 1, U7_3_4,
			0, 2, 4, 1, U7_3_4,

			// triangles between the squares
			1, 1, 3, 1, U7_3_4,
			2, 1, 3, 1, U7_3_4,
			3, 1, 3, 1, U7_3_4,

			// elongating squares next to cupola squares
			1, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,6,3) + Math.PI/2,
			2, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,6,3) + Math.PI/2,
			3, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,6,3) + Math.PI/2,

			// elongating squares next to cupola triangles
			4, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,6,4) + Math.PI/2,
			5, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,6,4) + Math.PI/2,
			6, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,6,4) + Math.PI/2,

			// squares of second cupola
			10, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,6,3) + Math.PI/2,
			11, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,6,3) + Math.PI/2,
			12, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,6,3) + Math.PI/2,

			// triangles between the squares
			13, 1, 3, 1, U7_3_4,
			14, 1, 3, 1, U7_3_4,
			15, 1, 3, 1, U7_3_4,

			// central triangle of second cupola
			13, 2, 3, 1, U7_3_4
		},

		new double[] // J37 - elongated square gyrobicupola
		{
			26,

			// central square of first cupola
			4, 1,

			// surrounding squares
			0, 0, 4, 1, U10_4_4,
			0, 1, 4, 1, U10_4_4,
			0, 2, 4, 1, U10_4_4,
			0, 3, 4, 1, U10_4_4,

			// triangles between the squares
			1, 1, 3, 1, U10_3_4,
			2, 1, 3, 1, U10_3_4,
			3, 1, 3, 1, U10_3_4,
			4, 1, 3, 1, U10_3_4,

			// elongating squares next to the squares
			1, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,8,3) + Math.PI/2,
			2, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,8,3) + Math.PI/2,
			3, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,8,3) + Math.PI/2,
			4, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,8,3) + Math.PI/2,

			// elongating squares next to the triangles
			5, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,8,4) + Math.PI/2,
			6, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,8,4) + Math.PI/2,
			7, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,8,4) + Math.PI/2,
			8, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,8,4) + Math.PI/2,

			// squares of the second cupola next to the elongating squares opposite the triangles of the first cupola
			13, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,8,3) + Math.PI/2,
			14, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,8,3) + Math.PI/2,
			15, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,8,3) + Math.PI/2,
			16, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,8,3) + Math.PI/2,

			// triangles of the second cupola next to the elongating squares opposite the squares of the first cupola
			9, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,4) + Math.PI/2,
			10, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,4) + Math.PI/2,
			11, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,4) + Math.PI/2,
			12, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,4) + Math.PI/2,

			// central square of the second cupola
			20, 2, 4, 1, U10_4_4
		},

		new double[] // J38 - elongated pentagonal orthobicupola
		{
			32,

			// central pentagon of first cupola
			5, 1,

			// surrounding squares
			0, 0, 4, 1, U27_4_5,
			0, 1, 4, 1, U27_4_5,
			0, 2, 4, 1, U27_4_5,
			0, 3, 4, 1, U27_4_5,
			0, 4, 4, 1, U27_4_5,

			// triangles between the squares
			1, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			2, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			3, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			4, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			5, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),

			// elongating squares next to the squares
			1, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,
			2, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,
			3, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,
			4, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,
			5, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,

			// elongating squares next to the triangles
			6, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,
			7, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,
			8, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,
			9, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,
			10, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,

			// squares of second cupola opposite squares of first cupola
			11, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,
			12, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,
			13, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,
			14, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,
			15, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,

			// triangles of second cupola opposite triangles of first cupola
			16, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,
			17, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,
			18, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,
			19, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,
			20, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,

			// central pentagon of second cupola
			21, 2, 5, 1, U27_4_5
		},

		new double[] // J39 - elongated pentagonal gyrobicupola
		{
			32,

			// central pentagon of first cupola
			5, 1,

			// surrounding squares
			0, 0, 4, 1, U27_4_5,
			0, 1, 4, 1, U27_4_5,
			0, 2, 4, 1, U27_4_5,
			0, 3, 4, 1, U27_4_5,
			0, 4, 4, 1, U27_4_5,

			// triangles between the squares
			1, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			2, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			3, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			4, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			5, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),

			// elongating squares next to the squares
			1, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,
			2, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,
			3, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,
			4, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,
			5, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,

			// elongating squares next to the triangles
			6, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,
			7, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,
			8, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,
			9, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,
			10, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,

			// squares of second cupola opposite triangles of first cupola
			16, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,
			17, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,
			18, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,
			19, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,
			20, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,

			// triangles of second cupola opposite squares of first cupola
			11, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,
			12, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,
			13, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,
			14, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,
			15, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,

			// central pentagon of second cupola

			21, 2, 5, 1, U27_4_5
		},

		new double[] // J40 - elongated pentagonal orthocupolarotunda
		{
			37,

			// central pentagon of cupola
			5, 1,

			// squares of cupola
			0, 0, 4, 1, U27_4_5,
			0, 1, 4, 1, U27_4_5,
			0, 2, 4, 1, U27_4_5,
			0, 3, 4, 1, U27_4_5,
			0, 4, 4, 1, U27_4_5,

			// triangles between squares
			1, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			2, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			3, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			4, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			5, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),

			// elongating squares next to squares of cupola
			1, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,
			2, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,
			3, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,
			4, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,
			5, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,

			// elongating squares next to triangles of cupola
			6, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,
			7, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,
			8, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,
			9, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,
			10, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,

			// outer pentagons of rotunda opposite triangles of cupola
			16, 2, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,
			17, 2, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,
			18, 2, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,
			19, 2, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,
			20, 2, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,

			// outer triangles of rotunda opposite squares of cupola
			11, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,
			12, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,
			13, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,
			14, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,
			15, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,

			// inner triangles of rotunda
			21, 2, 3, 1, U24_3_5,
			22, 2, 3, 1, U24_3_5,
			23, 2, 3, 1, U24_3_5,
			24, 2, 3, 1, U24_3_5,
			25, 2, 3, 1, U24_3_5,

			// central pentagon of rotunda
			31, 2, 5, 1, U24_3_5
		},

		new double[] // J41 - elongated pentagonal gyrocupolarotunda
		{
			37,

			// central pentagon of cupola
			5, 1,

			// squares of cupola
			0, 0, 4, 1, U27_4_5,
			0, 1, 4, 1, U27_4_5,
			0, 2, 4, 1, U27_4_5,
			0, 3, 4, 1, U27_4_5,
			0, 4, 4, 1, U27_4_5,

			// triangles between squares
			1, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			2, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			3, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			4, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			5, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),

			// elongating squares next to squares of cupola
			1, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,
			2, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,
			3, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,
			4, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,
			5, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + Math.PI/2,

			// elongating squares next to triangles of cupola
			6, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,
			7, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,
			8, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,
			9, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,
			10, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + Math.PI/2,

			// outer pentagons of rotunda
			11, 2, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,
			12, 2, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,
			13, 2, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,
			14, 2, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,
			15, 2, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,

			// outer triangles of rotunda
			16, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,
			17, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,
			18, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,
			19, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,
			20, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,

			// inner triangles of rotunda
			21, 2, 3, 1, U24_3_5,
			22, 2, 3, 1, U24_3_5,
			23, 2, 3, 1, U24_3_5,
			24, 2, 3, 1, U24_3_5,
			25, 2, 3, 1, U24_3_5,

			// central pentagon of rotunda
			31, 2, 5, 1, U24_3_5
		},

		new double[] // J42 - elongated pentagonal orthobirotunda
		{
			42,

			// central pentagon of first rotunda
			5, 1,

			// inner triangles of first rotunda
			0, 0, 3, 1, U24_3_5,
			0, 1, 3, 1, U24_3_5,
			0, 2, 3, 1, U24_3_5,
			0, 3, 3, 1, U24_3_5,
			0, 4, 3, 1, U24_3_5,

			// outer pentagons of first rotunda
			1, 1, 5, 1, U24_3_5,
			2, 1, 5, 1, U24_3_5,
			3, 1, 5, 1, U24_3_5,
			4, 1, 5, 1, U24_3_5,
			5, 1, 5, 1, U24_3_5,

			// outer triangles of first rotunda
			6, 2, 3, 1, U24_3_5,
			7, 2, 3, 1, U24_3_5,
			8, 2, 3, 1, U24_3_5,
			9, 2, 3, 1, U24_3_5,
			10, 2, 3, 1, U24_3_5,

			// elongating squares next to pentagons
			6, 3, 4, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,
			7, 3, 4, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,
			8, 3, 4, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,
			9, 3, 4, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,
			10, 3, 4, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,

			// elongating squares next to triangles
			11, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,
			12, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,
			13, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,
			14, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,
			15, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,

			// outer pentagons of second rotunda opposite pentagons of first rotunda
			16, 2, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,
			17, 2, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,
			18, 2, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,
			19, 2, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,
			20, 2, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,

			// outer triangles of second rotunda opposite triangles of first rotunda
			21, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,
			22, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,
			23, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,
			24, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,
			25, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,

			// inner triangles of second rotunda
			26, 2, 3, 1, U24_3_5,
			27, 2, 3, 1, U24_3_5,
			28, 2, 3, 1, U24_3_5,
			29, 2, 3, 1, U24_3_5,
			30, 2, 3, 1, U24_3_5,

			// central pentagon of second rotunda
			36, 2, 5, 1, U24_3_5
		},

		new double[] // J43 - elongated pentagonal gryobirotunda
		{
			42,

			// central pentagon of first rotunda
			5, 1,

			// inner triangles of first rotunda
			0, 0, 3, 1, U24_3_5,
			0, 1, 3, 1, U24_3_5,
			0, 2, 3, 1, U24_3_5,
			0, 3, 3, 1, U24_3_5,
			0, 4, 3, 1, U24_3_5,

			// outer pentagons of first rotunda
			1, 1, 5, 1, U24_3_5,
			2, 1, 5, 1, U24_3_5,
			3, 1, 5, 1, U24_3_5,
			4, 1, 5, 1, U24_3_5,
			5, 1, 5, 1, U24_3_5,

			// outer triangles of first rotunda
			6, 2, 3, 1, U24_3_5,
			7, 2, 3, 1, U24_3_5,
			8, 2, 3, 1, U24_3_5,
			9, 2, 3, 1, U24_3_5,
			10, 2, 3, 1, U24_3_5,

			// elongating squares next to pentagons
			6, 3, 4, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,
			7, 3, 4, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,
			8, 3, 4, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,
			9, 3, 4, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,
			10, 3, 4, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,

			// elongating squares next to triangles
			11, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,
			12, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,
			13, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,
			14, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,
			15, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,

			// outer pentagons of second rotunda opposite triangles of first rotunda
			21, 2, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,
			22, 2, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,
			23, 2, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,
			24, 2, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,
			25, 2, 5, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + Math.PI/2,

			// outer triangles of second rotunda opposite pentagons of first rotunda
			16, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,
			17, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,
			18, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,
			19, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,
			20, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + Math.PI/2,

			// inner triangles of second rotunda
			26, 2, 3, 1, U24_3_5,
			27, 2, 3, 1, U24_3_5,
			28, 2, 3, 1, U24_3_5,
			29, 2, 3, 1, U24_3_5,
			30, 2, 3, 1, U24_3_5,

			// central pentagon of second rotunda
			36, 2, 5, 1, U24_3_5
		},

		new double[] // J44 - gyroelongated triangular bicupola
		{
			26,

			// central triangle of first cupola
			3, 1,

			// surrounding squares
			0, 0, 4, 1, U7_3_4,
			0, 1, 4, 1, U7_3_4,
			0, 2, 4, 1, U7_3_4,

			// triangles between squares
			1, 1, 3, 1, U7_3_4,
			2, 1, 3, 1, U7_3_4,
			3, 1, 3, 1, U7_3_4,

			// ring of gyroelongating triangles next to cupola squares and triangles
			1, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,6,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(6,1),
			4, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,6,4) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(6,1),
			2, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,6,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(6,1),
			5, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,6,4) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(6,1),
			3, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,6,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(6,1),
			6, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,6,4) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(6,1),

			// second ring of gyroelongating triangles
			7, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(6,1),
			8, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(6,1),
			9, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(6,1),
			10, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(6,1),
			11, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(6,1),
			12, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(6,1),

			// squares of second cupola opposite squares of first cupola
			13, 2, 4, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(6,1) + GeometryUtils.trivalentVertexAngle(4,6,3),
			15, 2, 4, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(6,1) + GeometryUtils.trivalentVertexAngle(4,6,3),
			17, 2, 4, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(6,1) + GeometryUtils.trivalentVertexAngle(4,6,3),

			// triangles of second cupola opposite triangles of first cupola
			14, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(6,1) + GeometryUtils.trivalentVertexAngle(3,6,4),
			16, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(6,1) + GeometryUtils.trivalentVertexAngle(3,6,4),
			18, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(6,1) + GeometryUtils.trivalentVertexAngle(3,6,4),

			// central triangle of second cupola
			19, 2, 3, 1, U7_3_4
		},

		new double[] // J45 - gyroelongated square bicupola
		{
			34,

			// central square of first cupola
			4, 1,

			// surrounding squares
			0, 0, 4, 1, U10_4_4,
			0, 1, 4, 1, U10_4_4,
			0, 2, 4, 1, U10_4_4,
			0, 3, 4, 1, U10_4_4,

			// triangles between the squares
			1, 1, 3, 1, U10_3_4,
			2, 1, 3, 1, U10_3_4,
			3, 1, 3, 1, U10_3_4,
			4, 1, 3, 1, U10_3_4,

			// gyroelongating triangles next to squares and triangles of first cupola
			1, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,8,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1),
			5, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,4) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1),
			2, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,8,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1),
			6, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,4) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1),
			3, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,8,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1),
			7, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,4) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1),
			4, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,8,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1),
			8, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,4) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1),

			// second ring of gyroelongating triangles
			9, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(8,1),
			10, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(8,1),
			11, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(8,1),
			12, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(8,1),
			13, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(8,1),
			14, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(8,1),
			15, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(8,1),
			16, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(8,1),

			// squares of second cupola next to alternate gyroelongating triangles
			17, 1, 4, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1) + GeometryUtils.trivalentVertexAngle(4,8,3),
			19, 1, 4, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1) + GeometryUtils.trivalentVertexAngle(4,8,3),
			21, 1, 4, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1) + GeometryUtils.trivalentVertexAngle(4,8,3),
			23, 1, 4, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1) + GeometryUtils.trivalentVertexAngle(4,8,3),

			// triangles of second cupola next to alternate gyroelongating triangles
			18, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1) + GeometryUtils.trivalentVertexAngle(3,8,4),
			20, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1) + GeometryUtils.trivalentVertexAngle(3,8,4),
			22, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1) + GeometryUtils.trivalentVertexAngle(3,8,4),
			24, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(8,1) + GeometryUtils.trivalentVertexAngle(3,8,4),

			// central square of second cupola
			25, 2, 4, 1, U10_4_4
		},

		new double[] // J46 - gyroelongated pentagonal bicupola
		{
			42,

			// central pentagon of first cupola
			5, 1,

			// surrounding squares
			0, 0, 4, 1, U27_4_5,
			0, 1, 4, 1, U27_4_5,
			0, 2, 4, 1, U27_4_5,
			0, 3, 4, 1, U27_4_5,
			0, 4, 4, 1, U27_4_5,

			// triangles between squares
			1, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			2, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			3, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			4, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			5, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),

			// first ring of gyroelongating triangles next to squares and triangles of first cupola
			1, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			6, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			2, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			7, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			3, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			8, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			4, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			9, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			5, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			10, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),

			// second ring of gyroelongating triangles
			11, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			12, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			13, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			14, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			15, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			16, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			17, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			18, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			19, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			20, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),

			// squares of second cupola next to alternate gyroelongating triangles
			21, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			23, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			25, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			27, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			29, 2, 4, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),

			// triangles of second cupola next to alternate gyroelongating triangles
			22, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			24, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			26, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			28, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			30, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),

			// central pentagon of second cupola
			31, 2, 5, 1, U27_4_5
		},

		new double[] // J47 - gyroelongated pentagonal cupolarotunda
		{
			47,

			// central pentagon of cupola
			5, 1,

			// surrounding squares
			0, 0, 4, 1, U27_4_5,
			0, 1, 4, 1, U27_4_5,
			0, 2, 4, 1, U27_4_5,
			0, 3, 4, 1, U27_4_5,
			0, 4, 4, 1, U27_4_5,

			// triangles between squares
			1, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			2, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			3, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			4, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			5, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,4,10),

			// first ring of gyroelongating triangles next to squares and triangles of first cupola
			1, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			6, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			2, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			7, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			3, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			8, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			4, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			9, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			5, 2, 3, 1, GeometryUtils.trivalentVertexAngle(4,10,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			10, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,4) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),

			// second ring of gyroelongating triangles
			11, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			12, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			13, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			14, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			15, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			16, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			17, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			18, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			19, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			20, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),

			// pentagons of rotunda next to alternate gyroelongating triangles
			21, 2, 5, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(5,10,3),
			23, 2, 5, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(5,10,3),
			25, 2, 5, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(5,10,3),
			27, 2, 5, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(5,10,3),
			29, 2, 5, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(5,10,3),

			// triangles of rotunda next to remaining alternate gyroelongating triangles
			22, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(3,10,5),
			24, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(3,10,5),
			26, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(3,10,5),
			28, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(3,10,5),
			30, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(3,10,5),

			// inner triangles of rotunda
			31, 2, 3, 1, U24_3_5,
			32, 2, 3, 1, U24_3_5,
			33, 2, 3, 1, U24_3_5,
			34, 2, 3, 1, U24_3_5,
			35, 2, 3, 1, U24_3_5,

			// central pentagon of rotunda
			41, 2, 5, 1, U24_3_5
		},

		new double[] // J48 - gyroelongated pentagonal birotunda
		{
			52,

			// central pentagon of one rotunda
			5, 1,

			// inner ring of triangles
			0, 0, 3, 1, U24_3_5,
			0, 1, 3, 1, U24_3_5,
			0, 2, 3, 1, U24_3_5,
			0, 3, 3, 1, U24_3_5,
			0, 4, 3, 1, U24_3_5,

			// outer ring of pentagons
			1, 1, 5, 1, U24_3_5,
			2, 1, 5, 1, U24_3_5,
			3, 1, 5, 1, U24_3_5,
			4, 1, 5, 1, U24_3_5,
			5, 1, 5, 1, U24_3_5,

			// outer ring of triangles amongst pentagons
			6, 2, 3, 1, U24_3_5,
			7, 2, 3, 1, U24_3_5,
			8, 2, 3, 1, U24_3_5,
			9, 2, 3, 1, U24_3_5,
			10, 2, 3, 1, U24_3_5,

			// first ring of gyroelongating triangles next to pentagons and triangles of first rotunda
			6, 3, 3, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			11, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			7, 3, 3, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			12, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			8, 3, 3, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			13, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			9, 3, 3, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			14, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			10, 3, 3, 1, GeometryUtils.trivalentVertexAngle(5,10,3) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),
			15, 2, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,5) + GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1),

			// second ring of gyroelongating triangles
			16, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			17, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			18, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			19, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			20, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			21, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			22, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			23, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			24, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),
			25, 1, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(10,1),

			// pentagons of second rotunda next to alternate gyroelongating triangles
			26, 2, 5, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(5,10,3),
			28, 2, 5, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(5,10,3),
			30, 2, 5, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(5,10,3),
			32, 2, 5, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(5,10,3),
			34, 2, 5, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(5,10,3),

			// triangles of second rotunda next to remaining alternate gyroelongating triangles
			27, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(3,10,5),
			29, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(3,10,5),
			31, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(3,10,5),
			33, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(3,10,5),
			35, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(10,1) + GeometryUtils.trivalentVertexAngle(3,10,5),

			// inner ring of triangles of second rotunda
			36, 2, 3, 1, U24_3_5,
			37, 2, 3, 1, U24_3_5,
			38, 2, 3, 1, U24_3_5,
			39, 2, 3, 1, U24_3_5,
			40, 2, 3, 1, U24_3_5,

			// central pentagon of second rotunda
			46, 2, 5, 1, U24_3_5
		},

		new double[] // J49 - augmented triangular prism
		{
			8,

			// starting square
			4, 1,

			// the two ends of the prism
			0, 0, 3, 1, Math.PI/2,
			0, 2, 3, 1, Math.PI/2,

			// the other square on one side
			0, 1, 4, 1, Math.PI/3,

			// one triangle of a square pyramid opposite the second square
			0, 3, 3, 1, Math.PI/3 + GeometryUtils.trivalentVertexAngle(3,4,3),

			// the remaining sides of the square pyramid
			4, 1, 3, 1, R2_3_3,
			5, 2, 3, 1, R2_3_3,
			6, 2, 3, 1, R2_3_3
		},

		new double[] // J50 - biaugmented triangular prism
		{
			11,

			// starting square
			4, 1,

			// the two ends of the prism
			0, 0, 3, 1, Math.PI/2,
			0, 2, 3, 1, Math.PI/2,

			// triangles of two square pyramids at the other two sides
			0, 1, 3, 1, Math.PI/3 + GeometryUtils.trivalentVertexAngle(3,4,3),
			0, 3, 3, 1, Math.PI/3 + GeometryUtils.trivalentVertexAngle(3,4,3),

			// the remaining sides of the first square pyramid
			3, 1, 3, 1, R2_3_3,
			5, 2, 3, 1, R2_3_3,
			6, 2, 3, 1, R2_3_3,

			// the remaining sides of the first square pyramid
			4, 1, 3, 1, R2_3_3,
			8, 2, 3, 1, R2_3_3,
			9, 2, 3, 1, R2_3_3
		},

		new double[] // J51 - triaugmented triangular prism
		{
			14,

			// one end of the prism
			3, 1,

			// a pyramid on the first side of the prism
			0, 0, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,4,3),
			1, 1, 3, 1, R2_3_3,
			2, 2, 3, 1, R2_3_3,
			3, 2, 3, 1, R2_3_3,

			// a pyramid on the second side of the prism
			0, 1, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,4,3),
			5, 1, 3, 1, R2_3_3,
			6, 2, 3, 1, R2_3_3,
			7, 2, 3, 1, R2_3_3,

			// a pyramid on the second side of the prism
			0, 2, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,4,3),
			9, 1, 3, 1, R2_3_3,
			10, 2, 3, 1, R2_3_3,
			11, 2, 3, 1, R2_3_3,

			// the other end of the prism
			3, 1, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,4,3)
		},

		new double[] // J52 - augmented pentagonal prism
		{
			10,

			// pentagonal end
			5, 1,

			// four square edges
			0, 0, 4, 1, Math.PI/2,
			0, 1, 4, 1, Math.PI/2,
			0, 2, 4, 1, Math.PI/2,
			0, 3, 4, 1, Math.PI/2,

			// first triangle of square pyramid on fifth edge
			0, 4, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,4,3),

			// other three triangles of square pyramid
			5, 1, 3, 1, R2_3_3,
			6, 2, 3, 1, R2_3_3,
			7, 2, 3, 1, R2_3_3,

			// other end
			1, 2, 5, 1, Math.PI/2
		},

		new double[] // J53 - biaugmented pentagonal prism
		{
			13,

			// pentagonal end
			5, 1,

			// two square edges
			0, 0, 4, 1, Math.PI/2,
			0, 1, 4, 1, Math.PI/2,

			// first triangle of square pyramid on third edge of end
			0, 2, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,4,3),

			// other three triangles of square pyramid
			3, 1, 3, 1, R2_3_3,
			4, 2, 3, 1, R2_3_3,
			5, 2, 3, 1, R2_3_3,

			// square edge on fourth edge of end
			0, 3, 4, 1, Math.PI/2,

			// first triangle of square pyramid on fifth edge of end
			0, 4, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,4,3),

			// other three triangles of square pyramid
			8, 1, 3, 1, R2_3_3,
			9, 2, 3, 1, R2_3_3,
			10, 2, 3, 1, R2_3_3,

			// other end of prism
			1, 2, 5, 1, Math.PI/2
		},

		new double[] // J54 - augmented hexagonal prism
		{
			11,

			// hexagonal end of prism
			6, 1,

			// five square sides of prism
			0, 0, 4, 1, Math.PI/2,
			0, 1, 4, 1, Math.PI/2,
			0, 2, 4, 1, Math.PI/2,
			0, 3, 4, 1, Math.PI/2,
			0, 4, 4, 1, Math.PI/2,

			// first triangle of square pyramid on sixth side of prism
			0, 5, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,4,3),

			// remaining three sides of square pyramid
			6, 1, 3, 1, R2_3_3,
			7, 2, 3, 1, R2_3_3,
			8, 2, 3, 1, R2_3_3,

			// other end of prism
			1, 2, 6, 1, Math.PI/2
		},

		new double[] // J55 - parabiaugmented hexagonal prism
		{
			14,

			// hexagonal end of prism
			6, 1,

			// two square sides of prism
			0, 0, 4, 1, Math.PI/2,
			0, 1, 4, 1, Math.PI/2,

			// first triangle of square pyramid on third side of prism
			0, 2, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,4,3),

			// remaining three sides of square pyramid
			3, 1, 3, 1, R2_3_3,
			4, 2, 3, 1, R2_3_3,
			5, 2, 3, 1, R2_3_3,

			// squares on fourth and fifth sides of prism
			0, 3, 4, 1, Math.PI/2,
			0, 4, 4, 1, Math.PI/2,

			// first triangle of square pyramid on sixth side of prism
			0, 5, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,4,3),

			// remaining three sides of square pyramid
			9, 1, 3, 1, R2_3_3,
			10, 2, 3, 1, R2_3_3,
			11, 2, 3, 1, R2_3_3,

			// other end of prism
			1, 2, 6, 1, Math.PI/2
		},

		new double[] // J56 - metabiaugmented hexagonal prism
		{
			14,

			// hexagonal end of prism
			6, 1,

			// three square sides of prism
			0, 0, 4, 1, Math.PI/2,
			0, 1, 4, 1, Math.PI/2,
			0, 2, 4, 1, Math.PI/2,

			// first triangle of square pyramid on fourth side of prism
			0, 3, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,4,3),

			// remaining three sides of square pyramid
			4, 1, 3, 1, R2_3_3,
			5, 2, 3, 1, R2_3_3,
			6, 2, 3, 1, R2_3_3,

			// square on fifth side of prism
			0, 4, 4, 1, Math.PI/2,

			// first triangle of square pyramid on sixth side of prism
			0, 5, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,4,3),

			// remaining three sides of square pyramid
			9, 1, 3, 1, R2_3_3,
			10, 2, 3, 1, R2_3_3,
			11, 2, 3, 1, R2_3_3,

			// other end of prism
			1, 2, 6, 1, Math.PI/2
		},

		new double[] // J57 - triaugmented hexagonal prism
		{
			17,

			// hexagonal end of prism
			6, 1,

			// square on first side of prism
			0, 0, 4, 1, Math.PI/2,

			// first triangle of square pyramid on second side of prism
			0, 1, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,4,3),

			// remaining three sides of square pyramid
			2, 1, 3, 1, R2_3_3,
			3, 2, 3, 1, R2_3_3,
			4, 2, 3, 1, R2_3_3,

			// square on third side of prism
			0, 2, 4, 1, Math.PI/2,

			// first triangle of square pyramid on fourth side of prism
			0, 3, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,4,3),

			// remaining three sides of square pyramid
			7, 1, 3, 1, R2_3_3,
			8, 2, 3, 1, R2_3_3,
			9, 2, 3, 1, R2_3_3,

			// square on fifth side of prism
			0, 4, 4, 1, Math.PI/2,

			// first triangle of square pyramid on sixth side of prism
			0, 5, 3, 1, Math.PI/2 + GeometryUtils.trivalentVertexAngle(3,4,3),

			// remaining three sides of square pyramid
			12, 1, 3, 1, R2_3_3,
			13, 2, 3, 1, R2_3_3,
			14, 2, 3, 1, R2_3_3,

			// other end of prism
			1, 2, 6, 1, Math.PI/2
		},

		new double[] // J58 - augmented dodecahedron
		{
			16,

			// first pentagon
			5, 1,

			// ring of pentagons around the first
			0, 0, 5, 1, R5_5_5,
			0, 1, 5, 1, R5_5_5,
			0, 2, 5, 1, R5_5_5,
			0, 3, 5, 1, R5_5_5,
			0, 4, 5, 1, R5_5_5,

			// second ring of pentagons
			1, 2, 5, 1, R5_5_5,
			2, 2, 5, 1, R5_5_5,
			3, 2, 5, 1, R5_5_5,
			4, 2, 5, 1, R5_5_5,
			5, 2, 5, 1, R5_5_5,

			// first triangle of pentagonal pyramid on last side of dodecahedron
			6, 3, 3, 1, R5_5_5 + GeometryUtils.trivalentVertexAngle(3,5,3),

			// the remaining four sides of the pentagonal pyramid
			11, 1, 3, 1, R4_3_3,
			12, 2, 3, 1, R4_3_3,
			13, 2, 3, 1, R4_3_3,
			14, 2, 3, 1, R4_3_3
		},

		new double[] // J59 - parabiaugmented dodecahedron
		{
			20,

			// first pentagon
			5, 1,

			// four pentagons adjacent to first pentagon
			0, 0, 5, 1, R5_5_5,
			0, 1, 5, 1, R5_5_5,
			0, 2, 5, 1, R5_5_5,
			0, 3, 5, 1, R5_5_5,

			// first triangle of a pentagonal pyramid on the fifth side of the first pentagon
			0, 4, 3, 1, R5_5_5 + GeometryUtils.trivalentVertexAngle(3,5,3),

			// remaining four triangles in pentagonal pyramid
			5, 1, 3, 1, R4_3_3,
			6, 2, 3, 1, R4_3_3,
			7, 2, 3, 1, R4_3_3,
			8, 2, 3, 1, R4_3_3,

			// first two pentagons of second ring of pentagons
			1, 2, 5, 1, R5_5_5,
			2, 2, 5, 1, R5_5_5,

			// first triangle of a pentagonal pyramid on the third pentagon of the second ring of pentagons
			3, 2, 3, 1, R5_5_5 + GeometryUtils.trivalentVertexAngle(3,5,3),

			// remaining four triangles in pentagonal pyramid
			12, 1, 3, 1, R4_3_3,
			13, 2, 3, 1, R4_3_3,
			14, 2, 3, 1, R4_3_3,
			15, 2, 3, 1, R4_3_3,

			// pentagons four and five of second ring of pentagons
			4, 2, 5, 1, R5_5_5,
			4, 3, 5, 1, R5_5_5,

			// final pentagon opposite the first
			10, 3, 5, 1, R5_5_5
		},

		new double[] // J60 - metabiaugmented dodecahedron
		{
			20,

			// first pentagon
			5, 1,

			// ring of pentagons around the first
			0, 0, 5, 1, R5_5_5,
			0, 1, 5, 1, R5_5_5,
			0, 2, 5, 1, R5_5_5,
			0, 3, 5, 1, R5_5_5,
			0, 4, 5, 1, R5_5_5,

			// first two pentagons of second ring of pentagons
			1, 2, 5, 1, R5_5_5,
			2, 2, 5, 1, R5_5_5,

			// first triangle of pentagonal pyramid on third pentagon of second ring of pentagons
			3, 2, 3, 1, R5_5_5 + GeometryUtils.trivalentVertexAngle(3,5,3),

			// the remaining four sides of the pentagonal pyramid
			8, 1, 3, 1, R4_3_3,
			9, 2, 3, 1, R4_3_3,
			10, 2, 3, 1, R4_3_3,
			11, 2, 3, 1, R4_3_3,

			// fourth pentagon of second ring of pentagons
			4, 2, 5, 1, R5_5_5,

			// first triangle of pentagonal pyramid on fifth pentagon of second ring of pentagons
			5, 2, 3, 1, R5_5_5 + GeometryUtils.trivalentVertexAngle(3,5,3),

			// the remaining four sides of the pentagonal pyramid
			14, 1, 3, 1, R4_3_3,
			15, 2, 3, 1, R4_3_3,
			16, 2, 3, 1, R4_3_3,
			17, 2, 3, 1, R4_3_3,

			// final pentagon opposite the first
			6, 3, 5, 1, R5_5_5
		},

		new double[] // J61 - triaugmented dodecahedron
		{
			24,

			// first pentagon
			5, 1,

			// four pentagons adjacent to first pentagon
			0, 0, 5, 1, R5_5_5,
			0, 1, 5, 1, R5_5_5,
			0, 2, 5, 1, R5_5_5,
			0, 3, 5, 1, R5_5_5,

			// first triangle of a pentagonal pyramid on the fifth side of the first pentagon
			0, 4, 3, 1, R5_5_5 + GeometryUtils.trivalentVertexAngle(3,5,3),

			// remaining four triangles in pentagonal pyramid
			5, 1, 3, 1, R4_3_3,
			6, 2, 3, 1, R4_3_3,
			7, 2, 3, 1, R4_3_3,
			8, 2, 3, 1, R4_3_3,

			// first pentagon of second ring of pentagons
			1, 2, 5, 1, R5_5_5,

			// first triangle of a pentagonal pyramid on the second pentagon of the second ring of pentagons
			2, 2, 3, 1, R5_5_5 + GeometryUtils.trivalentVertexAngle(3,5,3),

			// remaining four triangles in pentagonal pyramid
			11, 1, 3, 1, R4_3_3,
			12, 2, 3, 1, R4_3_3,
			13, 2, 3, 1, R4_3_3,
			14, 2, 3, 1, R4_3_3,

			// third pentagon of second ring of pentagons
			3, 2, 5, 1, R5_5_5,

			// first triangle of a pentagonal pyramid on the fourth pentagon of the second ring of pentagons
			4, 2, 3, 1, R5_5_5 + GeometryUtils.trivalentVertexAngle(3,5,3),

			// remaining four triangles in pentagonal pyramid
			17, 1, 3, 1, R4_3_3,
			18, 2, 3, 1, R4_3_3,
			19, 2, 3, 1, R4_3_3,
			20, 2, 3, 1, R4_3_3,

			// fifth pentagon of second ring of pentagons
			4, 3, 5, 1, R5_5_5,

			// final pentagon opposite the first
			10, 3, 5, 1, R5_5_5
		},

		new double[] // J62 - metabidiminished icosahedron
		{
			12,

			// ring of five triangles
			3, 1,
			0, 0, 3, 1, R4_3_3,
			1, 1, 3, 1, R4_3_3,
			2, 1, 3, 1, R4_3_3,
			3, 1, 3, 1, R4_3_3,

			// triangles next to first and second triangles in ring
			1, 2, 3, 1, R4_3_3,
			2, 2, 3, 1, R4_3_3,

			// pentagon next to third triangle in ring
			3, 2, 5, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(5,1),

			// triangle next to fourth triangle in ring
			4, 2, 3, 1, R4_3_3,

			// pentagon next to fifth triangle in ring
			5, 2, 5, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(5,1),

			// remaining two triangles
			5, 1, 3, 1, R4_3_3,
			10, 2, 3, 1, R4_3_3
		},

		new double[] // J63 - tridiminished icosahedron
		{
			8,

			// first triangle
			3, 1,

			// a triangle on each side
			0, 0, 3, 1, R4_3_3,
			0, 1, 3, 1, R4_3_3,
			0, 2, 3, 1, R4_3_3,

			// a pentagon between each pair of triangles
			1, 1, 5, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(5,1),
			2, 1, 5, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(5,1),
			3, 1, 5, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(5,1),

			// a triangle between the three pentagons
			4, 3, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(5,1)
		},

		new double[] // J64 - augmented tridiminished icosahedron
		{
			10,

			// first triangle
			3, 1,

			// a triangle on each side
			0, 0, 3, 1, R4_3_3,
			0, 1, 3, 1, R4_3_3,
			0, 2, 3, 1, R4_3_3,

			// a pentagon between each pair of triangles
			1, 1, 5, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(5,1),
			2, 1, 5, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(5,1),
			3, 1, 5, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(5,1),

			// a triangle at the end of pentagon, forming a triangular pyramid
			4, 3, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(5,1) + R1_3_3,
			5, 3, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(5,1) + R1_3_3,
			6, 3, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(5,1) + R1_3_3
		},

		new double[] // J65 - augmented truncated tetrahedron
		{
			14,

			// base hexagon
			6, 1,

			// triangles of truncated tetrahedron at sides one, three and five of the base hexagon
			0, 0, 3, 1, R2_3_3,
			0, 2, 3, 1, R2_3_3,
			0, 4, 3, 1, R2_3_3,

			// hexagons at sides two and four of the base hexagon
			0, 1, 6, 1, R1_3_3,
			0, 3, 6, 1, R1_3_3,

			// first triangle of triangular cupola at side six of the base hexagon
			0, 5, 3, 1, R1_3_3 + GeometryUtils.trivalentVertexAngle(3,6,4),

			// remaining ring of squares and triangles of triangular cupola
			6, 1, 4, 1, U7_3_4,
			7, 2, 3, 1, U7_3_4,
			8, 2, 4, 1, U7_3_4,
			9, 2, 3, 1, U7_3_4,
			10, 2, 4, 1, U7_3_4,

			// top triangle of triangular cupola
			7, 3, 3, 1, U7_3_4,

			// top triangle of truncated tetrahedron
			4, 3, 3, 1, R2_3_3
		},

		new double[] // J66 - augmented truncated cube
		{
			22,

			// base octagon
			8, 1,

			// base octagon surrounded by alternate octagons and triangles
			0, 0, 8, 1, R3_4_4,
			0, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,8),
			0, 2, 8, 1, R3_4_4,
			0, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,8),
			0, 4, 8, 1, R3_4_4,
			0, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,8),
			0, 6, 8, 1, R3_4_4,
			0, 7, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,8),

			// triangles at top four corners
			1, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,8),
			3, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,8),
			5, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,8),
			7, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,8),

			// first square of square cupola on top
			9, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,8,8) + GeometryUtils.trivalentVertexAngle(4,8,3),

			// remaining edge triangles and squares of square cupola
			13, 1, 3, 1, U10_3_4,
			14, 2, 4, 1, U10_3_4,
			15, 2, 3, 1, U10_3_4,
			16, 2, 4, 1, U10_3_4,
			17, 2, 3, 1, U10_3_4,
			18, 2, 4, 1, U10_3_4,
			19, 2, 3, 1, U10_3_4,

			// top square of square cupola
			13, 2, 4, 1, U10_4_4
		},

		new double[] // J67 - biaugmented truncated cube
		{
			30,

			// base octagon
			8, 1,

			// sides one and two of base octagon adjoin an octagon and a triangle
			0, 0, 8, 1, R3_4_4,
			0, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,8),

			// side three of base octagon adjoins the first triangle of a square cupola
			0, 2, 3, 1, R3_4_4 + GeometryUtils.trivalentVertexAngle(3,8,4),

			// remaining edge squares and triangles of square cupola
			3, 1, 4, 1, U10_3_4,
			4, 2, 3, 1, U10_3_4,
			5, 2, 4, 1, U10_3_4,
			6, 2, 3, 1, U10_3_4,
			7, 2, 4, 1, U10_3_4,
			8, 2, 3, 1, U10_3_4,
			9, 2, 4, 1, U10_3_4,

			// top square of square cupola
			4, 3, 4, 1, U10_4_4,

			// sides four, five and six of base octagon adjoin a triangle, an octagon and a triangle respectively
			0, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,8),
			0, 4, 8, 1, R3_4_4,
			0, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,8),

			// side seven of base octagon adjoins the first triangle of a square cupola
			0, 6, 3, 1, R3_4_4 + GeometryUtils.trivalentVertexAngle(3,8,4),

			// remaining edge squares and triangles of square cupola
			15, 1, 4, 1, U10_3_4,
			16, 2, 3, 1, U10_3_4,
			17, 2, 4, 1, U10_3_4,
			18, 2, 3, 1, U10_3_4,
			19, 2, 4, 1, U10_3_4,
			20, 2, 3, 1, U10_3_4,
			21, 2, 4, 1, U10_3_4,

			// top square of square cupola
			16, 3, 4, 1, U10_4_4,

			// side eight of base octagon adjoins a triangle
			0, 7, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,8),

			// the top octagon
			1, 4, 8, 1, R3_4_4,

			// the four top triangles
			25, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,8),
			25, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,8),
			25, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,8),
			25, 7, 3, 1, GeometryUtils.trivalentVertexAngle(3,8,8)
		},

		new double[] // J68 - augmented truncated dodecahedron
		{
			42,

			// base decagon
			10, 1,

			// a ring of decagons and triangles around the base decagon
			0, 0, 10, 1, R5_5_5,
			0, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			0, 2, 10, 1, R5_5_5,
			0, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			0, 4, 10, 1, R5_5_5,
			0, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			0, 6, 10, 1, R5_5_5,
			0, 7, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			0, 8, 10, 1, R5_5_5,
			0, 9, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// a further triangle, decagon and triangle around the first decagon in the ring of decagons
			1, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			1, 4, 10, 1, R5_5_5,
			1, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// a further triangle, decagon and triangle around the second decagon in the ring of decagons
			3, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			3, 4, 10, 1, R5_5_5,
			3, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// a further triangle, decagon and triangle around the third decagon in the ring of decagons
			5, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			5, 4, 10, 1, R5_5_5,
			5, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// a further triangle, decagon and triangle around the fourth decagon in the ring of decagons
			7, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			7, 4, 10, 1, R5_5_5,
			7, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// a further triangle, decagon and triangle around the fifth decagon in the ring of decagons
			9, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			9, 4, 10, 1, R5_5_5,
			9, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// the top five triangles of the truncated dodecahedron
			12, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			15, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			18, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			21, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			24, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// the first square of a pentagonal cupola on top
			26, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,10,10) + GeometryUtils.trivalentVertexAngle(4,10,3),

			// the remaining triangles and squares around the pentagonal cupola
			31, 1, 3, 1, U27_3_4,
			32, 2, 4, 1, U27_3_4,
			33, 2, 3, 1, U27_3_4,
			34, 2, 4, 1, U27_3_4,
			35, 2, 3, 1, U27_3_4,
			36, 2, 4, 1, U27_3_4,
			37, 2, 3, 1, U27_3_4,
			38, 2, 4, 1, U27_3_4,
			39, 2, 3, 1, U27_3_4,

			// the top pentagon of the pentagonal cupola
			31, 2, 5, 1, U27_4_5
		},

		new double[] // J69 - parabiaugmented truncated dodecahedron
		{
			52,

			// base decagon
			10, 1,

			// a ring of four decagons and four triangles around the base decagon
			0, 0, 10, 1, R5_5_5,
			0, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			0, 2, 10, 1, R5_5_5,
			0, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			0, 4, 10, 1, R5_5_5,
			0, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			0, 6, 10, 1, R5_5_5,
			0, 7, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// the first triangle of a pentagonal cupola where the fifth decagon would be
			0, 8, 3, 1, R5_5_5 + GeometryUtils.trivalentVertexAngle(3,10,4),

			// the remaining squares and triangles around the pentagonal cupola
			9, 1, 4, 1, U27_3_4,
			10, 2, 3, 1, U27_3_4,
			11, 2, 4, 1, U27_3_4,
			12, 2, 3, 1, U27_3_4,
			13, 2, 4, 1, U27_3_4,
			14, 2, 3, 1, U27_3_4,
			15, 2, 4, 1, U27_3_4,
			16, 2, 3, 1, U27_3_4,
			17, 2, 4, 1, U27_3_4,

			// the top pentagon of the pentagonal cupola
			10, 3, 5, 1, U27_4_5,

			// the final triangle in the ring around the base decagon
			0, 9, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// a further triangle, decagon and triangle around the first decagon in the ring of decagons
			1, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			1, 4, 10, 1, R5_5_5,
			1, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// a further triangle, decagon and triangle around the second decagon in the ring of decagons
			3, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			3, 4, 10, 1, R5_5_5,
			3, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// two triangles around the third decagon in the ring of decagons
			5, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			5, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// the first triangle of a pentagonal cupola where the third decagon in the outer ring would be
			5, 4, 3, 1, R5_5_5 + GeometryUtils.trivalentVertexAngle(3,10,4),

			// the remaining squares and triangles around the pentagonal cupola
			29, 1, 4, 1, U27_3_4,
			30, 2, 3, 1, U27_3_4,
			31, 2, 4, 1, U27_3_4,
			32, 2, 3, 1, U27_3_4,
			33, 2, 4, 1, U27_3_4,
			34, 2, 3, 1, U27_3_4,
			35, 2, 4, 1, U27_3_4,
			36, 2, 3, 1, U27_3_4,
			37, 2, 4, 1, U27_3_4,

			// the top pentagon of the pentagonal cupola
			30, 3, 5, 1, U27_4_5,

			// a further triangle, decagon and triangle around the fourth decagon in the ring of decagons
			7, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			7, 4, 10, 1, R5_5_5,
			7, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// a further decagon and triangle around the fifth decagon (which is replaced by a pentagonal cupola) in the ring of decagons
			7, 6, 10, 1, R5_5_5,
			7, 7, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// a further triangle around the fifth decagon (which is replaced by a pentagonal cupola) in the ring of decagons
			22, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// the top decagon
			22, 6, 10, 1, R5_5_5,

			// the top five triangles
			46, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			46, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			46, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			46, 7, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			46, 9, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10)
		},

		new double[] // J70 - metabiaugmented truncated dodecahedron
		{
			52,

			// base decagon
			10, 1,

			// a ring of four decagons and four triangles around the base decagon
			0, 0, 10, 1, R5_5_5,
			0, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			0, 2, 10, 1, R5_5_5,
			0, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			0, 4, 10, 1, R5_5_5,
			0, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			0, 6, 10, 1, R5_5_5,
			0, 7, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// the first triangle of a pentagonal cupola where the fifth decagon would be
			0, 8, 3, 1, R5_5_5 + GeometryUtils.trivalentVertexAngle(3,10,4),

			// the remaining squares and triangles around the pentagonal cupola
			9, 1, 4, 1, U27_3_4,
			10, 2, 3, 1, U27_3_4,
			11, 2, 4, 1, U27_3_4,
			12, 2, 3, 1, U27_3_4,
			13, 2, 4, 1, U27_3_4,
			14, 2, 3, 1, U27_3_4,
			15, 2, 4, 1, U27_3_4,
			16, 2, 3, 1, U27_3_4,
			17, 2, 4, 1, U27_3_4,

			// the top pentagon of the pentagonal cupola
			10, 3, 5, 1, U27_4_5,

			// the final triangle in the ring around the base decagon
			0, 9, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// a further triangle, decagon and triangle around the first decagon in the ring of decagons
			1, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			1, 4, 10, 1, R5_5_5,
			1, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// a further triangle, decagon and triangle around the second decagon in the ring of decagons
			3, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			3, 4, 10, 1, R5_5_5,
			3, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// a further triangle, decagon and triangle around the third decagon in the ring of decagons
			5, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			5, 4, 10, 1, R5_5_5,
			5, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// a further triangle, decagon and triangle around the fourth decagon in the ring of decagons
			7, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			7, 4, 10, 1, R5_5_5,
			7, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// a further decagon and triangle around the fifth decagon (which is replaced by a pentagonal cupola) in the ring of decagons
			7, 6, 10, 1, R5_5_5,
			7, 7, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// a further triangle around the fifth decagon (which is replaced by a pentagonal cupola) in the ring of decagons
			22, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// the first triangle of a pentagonal cupola where the top decagon would be
			22, 6, 3, 1, R5_5_5 + GeometryUtils.trivalentVertexAngle(3,10,4),

			// the remaining squares and triangles around the pentagonal cupola
			36, 1, 4, 1, U27_3_4,
			37, 2, 3, 1, U27_3_4,
			38, 2, 4, 1, U27_3_4,
			39, 2, 3, 1, U27_3_4,
			40, 2, 4, 1, U27_3_4,
			41, 2, 3, 1, U27_3_4,
			42, 2, 4, 1, U27_3_4,
			43, 2, 3, 1, U27_3_4,
			44, 2, 4, 1, U27_3_4,

			// the top pentagon of the pentagonal cupola
			37, 3, 5, 1, U27_4_5,

			// the top five triangles
			22, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			25, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			28, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			31, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			33, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10)
		},

		new double[] // J71 - triaugmented truncated dodecahedron
		{
			62,

			// base decagon
			10, 1,

			// a ring of four decagons and four triangles around the base decagon
			0, 0, 10, 1, R5_5_5,
			0, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			0, 2, 10, 1, R5_5_5,
			0, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			0, 4, 10, 1, R5_5_5,
			0, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			0, 6, 10, 1, R5_5_5,
			0, 7, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// the first triangle of a pentagonal cupola where the fifth decagon would be
			0, 8, 3, 1, R5_5_5 + GeometryUtils.trivalentVertexAngle(3,10,4),

			// the remaining squares and triangles around the pentagonal cupola
			9, 1, 4, 1, U27_3_4,
			10, 2, 3, 1, U27_3_4,
			11, 2, 4, 1, U27_3_4,
			12, 2, 3, 1, U27_3_4,
			13, 2, 4, 1, U27_3_4,
			14, 2, 3, 1, U27_3_4,
			15, 2, 4, 1, U27_3_4,
			16, 2, 3, 1, U27_3_4,
			17, 2, 4, 1, U27_3_4,

			// the top pentagon of the pentagonal cupola
			10, 3, 5, 1, U27_4_5,

			// the final triangle in the ring around the base decagon
			0, 9, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// a further triangle, decagon and triangle around the first decagon in the ring of decagons
			1, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			1, 4, 10, 1, R5_5_5,
			1, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// two further triangles around the second decagon in the ring of decagons
			3, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			3, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// the first triangle of a pentagonal cupola where the second decagon in the outer ring would be
			3, 4, 3, 1, R5_5_5 + GeometryUtils.trivalentVertexAngle(3,10,4),

			// the remaining squares and triangles around the pentagonal cupola
			26, 1, 4, 1, U27_3_4,
			27, 2, 3, 1, U27_3_4,
			28, 2, 4, 1, U27_3_4,
			29, 2, 3, 1, U27_3_4,
			30, 2, 4, 1, U27_3_4,
			31, 2, 3, 1, U27_3_4,
			32, 2, 4, 1, U27_3_4,
			33, 2, 3, 1, U27_3_4,
			34, 2, 4, 1, U27_3_4,

			// the top pentagon of the pentagonal cupola
			27, 3, 5, 1, U27_4_5,

			// a further triangle, decagon and triangle around the third decagon in the ring of decagons
			5, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			5, 4, 10, 1, R5_5_5,
			5, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// two further triangles around the fourth decagon in the ring of decagons
			7, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			7, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// the first triangle of a pentagonal cupola where the fourth decagon in the outer ring would be
			7, 4, 3, 1, R5_5_5 + GeometryUtils.trivalentVertexAngle(3,10,4),

			// the remaining squares and triangles around the pentagonal cupola
			42, 1, 4, 1, U27_3_4,
			43, 2, 3, 1, U27_3_4,
			44, 2, 4, 1, U27_3_4,
			45, 2, 3, 1, U27_3_4,
			46, 2, 4, 1, U27_3_4,
			47, 2, 3, 1, U27_3_4,
			48, 2, 4, 1, U27_3_4,
			49, 2, 3, 1, U27_3_4,
			50, 2, 4, 1, U27_3_4,

			// the top pentagon of the pentagonal cupola
			43, 3, 5, 1, U27_4_5,

			// a further decagon and triangle around the fifth decagon (which is replaced by a pentagonal cupola) in the ring of decagons
			7, 6, 10, 1, R5_5_5,
			7, 7, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// a further triangle around the fifth decagon (which is replaced by a pentagonal cupola) in the ring of decagons
			22, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),

			// the top decagon
			22, 6, 10, 1, R5_5_5,

			// the top five triangles
			56, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			56, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			56, 5, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			56, 7, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10),
			56, 9, 3, 1, GeometryUtils.trivalentVertexAngle(3,10,10)
		},

		new double[] // J72 - gyrate rhombicosidodecahedron
		{
			62,
			5, 1,

			// ring around first pentagon
			0, 0, 4, 1, U27_4_5,
			0, 1, 4, 1, U27_4_5,
			0, 2, 4, 1, U27_4_5,
			0, 3, 4, 1, U27_4_5,
			0, 4, 4, 1, U27_4_5,
			1, 1, 3, 1, U27_3_4,
			2, 1, 3, 1, U27_3_4,
			3, 1, 3, 1, U27_3_4,
			4, 1, 3, 1, U27_3_4,
			5, 1, 3, 1, U27_3_4,

			// each adjacent pentagon and rings around them
			1, 2, 5, 1, U27_4_5,
			6, 2, 4, 1, U27_3_4,
			12, 2, 3, 1, U27_3_4,
			13, 2, 4, 1, U27_3_4,
			14, 2, 3, 1, U27_3_4,
			15, 2, 4, 1, U27_3_4,

			2, 2, 5, 1, U27_4_5,
			7, 2, 4, 1, U27_3_4,
			18, 2, 3, 1, U27_3_4,
			19, 2, 4, 1, U27_3_4,
			20, 2, 3, 1, U27_3_4,
			21, 2, 4, 1, U27_3_4,

			4, 2, 5, 1, U27_4_5,
			9, 2, 4, 1, U27_3_4,
			24, 2, 3, 1, U27_3_4,
			25, 2, 4, 1, U27_3_4,
			26, 2, 3, 1, U27_3_4,
			27, 2, 4, 1, U27_3_4,

			3, 2, 5, 1, U27_4_5,
			8, 2, 4, 1, U27_3_4,
			30, 2, 3, 1, U27_3_4,
			31, 2, 4, 1, U27_3_4,
			32, 2, 3, 1, U27_3_4,
			33, 2, 4, 1, U27_3_4,

			5, 2, 5, 1, U27_4_5,
			10, 2, 4, 1, U27_3_4,
			36, 2, 3, 1, U27_3_4,
			37, 2, 4, 1, U27_3_4,
			38, 2, 3, 1, U27_3_4,
			39, 2, 4, 1, U27_3_4,

			// next surrounding ring of pentagons and squares between them
			16, 1, 5, 1, U27_4_5,
			15, 1, 4, 1, U27_3_4,

			22, 1, 5, 1, U27_4_5,
			21, 1, 4, 1, U27_3_4,

			28, 1, 5, 1, U27_4_5,
			27, 1, 4, 1, U27_3_4,

			34, 1, 5, 1, U27_4_5,
			33, 1, 4, 1, U27_3_4,

			40, 1, 5, 1, U27_4_5,
			39, 1, 4, 1, U27_3_4,

			// the gyrated pentagonal cupola
			41, 2, 3, 1, U27_4_5 + GeometryUtils.trivalentVertexAngle(3,10,4) - GeometryUtils.trivalentVertexAngle(4,10,3),
			51, 1, 4, 1, U27_3_4,
			52, 2, 3, 1, U27_3_4,
			53, 2, 4, 1, U27_3_4,
			54, 2, 3, 1, U27_3_4,
			55, 2, 4, 1, U27_3_4,
			56, 2, 3, 1, U27_3_4,
			57, 2, 4, 1, U27_3_4,
			58, 2, 3, 1, U27_3_4,
			59, 2, 4, 1, U27_3_4,
			60, 3, 5, 1, U27_4_5
		},

		new double[] // J73 - parabigyrate rhombicosidodecahedron
		{
			62,

			// a 'strip' of pentagon-square-pentagon between the two gyrated caps
			5, 1,
			0, 1, 4, 1, U27_4_5,
			1, 2, 5, 1, U27_4_5,

			// a 'strip' of square-triangle-square-triangle-square between the two gyrated caps
			0, 3, 4, 1, U27_4_5,
			3, 1, 3, 1, U27_3_4,
			4, 1, 4, 1, U27_3_4,
			5, 2, 3, 1, U27_3_4,
			6, 2, 4, 1, U27_3_4,

			// eight further strips
			3, 2, 5, 1, U27_4_5,
			8, 1, 4, 1, U27_4_5,
			9, 2, 5, 1, U27_4_5,

			8, 3, 4, 1, U27_4_5,
			11, 1, 3, 1, U27_3_4,
			12, 1, 4, 1, U27_3_4,
			13, 2, 3, 1, U27_3_4,
			14, 2, 4, 1, U27_3_4,

			11, 2, 5, 1, U27_4_5,
			16, 1, 4, 1, U27_4_5,
			17, 2, 5, 1, U27_4_5,

			16, 3, 4, 1, U27_4_5,
			19, 1, 3, 1, U27_3_4,
			20, 1, 4, 1, U27_3_4,
			21, 2, 3, 1, U27_3_4,
			22, 2, 4, 1, U27_3_4,

			19, 2, 5, 1, U27_4_5,
			24, 1, 4, 1, U27_4_5,
			25, 2, 5, 1, U27_4_5,

			24, 3, 4, 1, U27_4_5,
			27, 1, 3, 1, U27_3_4,
			28, 1, 4, 1, U27_3_4,
			29, 2, 3, 1, U27_3_4,
			30, 2, 4, 1, U27_3_4,

			27, 2, 5, 1, U27_4_5,
			32, 1, 4, 1, U27_4_5,
			33, 2, 5, 1, U27_4_5,

			32, 3, 4, 1, U27_4_5,
			35, 1, 3, 1, U27_3_4,
			36, 1, 4, 1, U27_3_4,
			37, 2, 3, 1, U27_3_4,
			38, 2, 4, 1, U27_3_4,

			// one gyrated pentagonal cupola
			0, 4, 3, 1, U27_4_5 + GeometryUtils.trivalentVertexAngle(3,10,4) - GeometryUtils.trivalentVertexAngle(4,10,3),
			40, 1, 4, 1, U27_3_4,
			41, 2, 3, 1, U27_3_4,
			42, 2, 4, 1, U27_3_4,
			43, 2, 3, 1, U27_3_4,
			44, 2, 4, 1, U27_3_4,
			45, 2, 3, 1, U27_3_4,
			46, 2, 4, 1, U27_3_4,
			47, 2, 3, 1, U27_3_4,
			48, 2, 4, 1, U27_3_4,
			49, 3, 5, 1, U27_4_5,

			// the other gyrated pentagonal cupola
			2, 3, 3, 1, U27_4_5 + GeometryUtils.trivalentVertexAngle(3,10,4) - GeometryUtils.trivalentVertexAngle(4,10,3),
			51, 1, 4, 1, U27_3_4,
			52, 2, 3, 1, U27_3_4,
			53, 2, 4, 1, U27_3_4,
			54, 2, 3, 1, U27_3_4,
			55, 2, 4, 1, U27_3_4,
			56, 2, 3, 1, U27_3_4,
			57, 2, 4, 1, U27_3_4,
			58, 2, 3, 1, U27_3_4,
			59, 2, 4, 1, U27_3_4,
			60, 3, 5, 1, U27_4_5
		},

		new double[] // J74 - metabigyrate rhombicosidodecahedron
		{
			62,

			// pentagon-square-pentagon between the gyrate caps
			5, 1,
			0, 0, 4, 1, U27_4_5,
			1, 2, 5, 1, U27_4_5,

			// square-triangle-square strip from cap to cap
			2, 3, 4, 1, U27_4_5,
			3, 1, 3, 1, U27_3_4,
			4, 1, 4, 1, U27_3_4,

			// pentagon-square-pentagon strip from cap to cap
			3, 2, 5, 1, U27_4_5,
			6, 1, 4, 1, U27_4_5,
			7, 2, 5, 1, U27_4_5,

			// square-triangle-square-triangle-square-triangle-square strip from cap to cap
			6, 3, 4, 1, U27_4_5,
			9, 1, 3, 1, U27_3_4,
			10, 1, 4, 1, U27_3_4,
			11, 2, 3, 1, U27_3_4,
			12, 2, 4, 1, U27_3_4,
			13, 2, 3, 1, U27_3_4,
			14, 1, 4, 1, U27_3_4,

			// pentagon-square-pentagon-square-pentagon strip from cap to cap
			9, 2, 5, 1, U27_4_5,
			16, 1, 4, 1, U27_4_5,
			17, 2, 5, 1, U27_4_5,
			18, 3, 4, 1, U27_4_5,
			19, 2, 5, 1, U27_4_5,

			// square-triangle-square-triangle-square between the end pentagons of the previous strip
			16, 2, 4, 1, U27_4_5,
			21, 1, 3, 1, U27_3_4,
			22, 2, 4, 1, U27_3_4,
			23, 2, 3, 1, U27_3_4,
			24, 2, 4, 1, U27_3_4,

			// pentagon between the end squares of the previous strip
			21, 2, 5, 1, U27_4_5,

			// square-triangle-square-triangle-square-triangle-square strip from cap to cap
			16, 3, 4, 1, U27_4_5,
			27, 1, 3, 1, U27_3_4,
			28, 2, 4, 1, U27_3_4,
			29, 2, 3, 1, U27_3_4,
			30, 1, 4, 1, U27_3_4,
			31, 2, 3, 1, U27_3_4,
			32, 2, 4, 1, U27_3_4,

			// pentagon-square-pentagon strip from cap to cap
			27, 2, 5, 1, U27_4_5,
			34, 2, 4, 1, U27_4_5,
			35, 2, 5, 1, U27_4_5,

			// square-triangle-square strip from cap to cap
			34, 3, 4, 1, U27_4_5,
			37, 1, 3, 1, U27_3_4,
			38, 2, 4, 1, U27_3_4,

			// one gyrated pentagonal cupola
			0, 1, 3, 1, U27_4_5 + GeometryUtils.trivalentVertexAngle(3,10,4) - GeometryUtils.trivalentVertexAngle(4,10,3),
			40, 1, 4, 1, U27_3_4,
			41, 2, 3, 1, U27_3_4,
			42, 2, 4, 1, U27_3_4,
			43, 2, 3, 1, U27_3_4,
			44, 2, 4, 1, U27_3_4,
			45, 2, 3, 1, U27_3_4,
			46, 2, 4, 1, U27_3_4,
			47, 2, 3, 1, U27_3_4,
			48, 2, 4, 1, U27_3_4,
			49, 3, 5, 1, U27_4_5,

			// the other gyrated pentagonal cupola
			0, 4, 3, 1, U27_4_5 + GeometryUtils.trivalentVertexAngle(3,10,4) - GeometryUtils.trivalentVertexAngle(4,10,3),
			51, 1, 4, 1, U27_3_4,
			52, 2, 3, 1, U27_3_4,
			53, 2, 4, 1, U27_3_4,
			54, 2, 3, 1, U27_3_4,
			55, 2, 4, 1, U27_3_4,
			56, 2, 3, 1, U27_3_4,
			57, 2, 4, 1, U27_3_4,
			58, 2, 3, 1, U27_3_4,
			59, 2, 4, 1, U27_3_4,
			60, 3, 5, 1, U27_4_5
		},

		new double[] // J75 - trigyrate rhombicosidodecahedron
		{
			62,

			// ring of pentagons and squares around first gyrate cap
			5, 1,
			0, 2, 4, 1, U27_4_5,
			1, 2, 5, 1, U27_4_5,
			2, 2, 4, 1, U27_4_5,
			3, 2, 5, 1, U27_4_5,
			4, 2, 4, 1, U27_4_5,
			5, 2, 5, 1, U27_4_5,
			6, 2, 4, 1, U27_4_5,
			7, 2, 5, 1, U27_4_5,
			8, 2, 4, 1, U27_4_5,

			// ring of pentagons and squares around second gyrate cap
			0, 3, 4, 1, U27_4_5,
			10, 2, 5, 1, U27_4_5,
			11, 3, 4, 1, U27_4_5,
			12, 2, 5, 1, U27_4_5,
			13, 3, 4, 1, U27_4_5,
			14, 2, 5, 1, U27_4_5,
			15, 3, 4, 1, U27_4_5,

			// ring of pentagons and squares around third gyrate cap
			4, 3, 4, 1, U27_4_5,
			17, 2, 5, 1, U27_4_5,
			18, 3, 4, 1, U27_4_5,

			// triangle and square in small space between three gyrate caps
			1, 3, 3, 1, U27_3_4,
			20, 1, 4, 1, U27_3_4,

			// triangles and squares in larger Y-shaped space between three gyrate caps
			5, 3, 3, 1, U27_3_4,
			22, 1, 4, 1, U27_3_4,
			23, 2, 3, 1, U27_3_4,
			24, 1, 4, 1, U27_3_4,
			25, 2, 3, 1, U27_3_4,
			24, 2, 4, 1, U27_3_4,
			27, 2, 3, 1, U27_3_4,

			// first gyrate cap
			0, 1, 3, 1, U27_4_5 + GeometryUtils.trivalentVertexAngle(3,10,4) - GeometryUtils.trivalentVertexAngle(4,10,3),
			29, 1, 4, 1, U27_3_4,
			30, 2, 3, 1, U27_3_4,
			31, 2, 4, 1, U27_3_4,
			32, 2, 3, 1, U27_3_4,
			33, 2, 4, 1, U27_3_4,
			34, 2, 3, 1, U27_3_4,
			35, 2, 4, 1, U27_3_4,
			36, 2, 3, 1, U27_3_4,
			37, 2, 4, 1, U27_3_4,
			38, 3, 5, 1, U27_4_5,

			// second gyrate cap
			0, 4, 3, 1, U27_4_5 + GeometryUtils.trivalentVertexAngle(3,10,4) - GeometryUtils.trivalentVertexAngle(4,10,3),
			40, 1, 4, 1, U27_3_4,
			41, 2, 3, 1, U27_3_4,
			42, 2, 4, 1, U27_3_4,
			43, 2, 3, 1, U27_3_4,
			44, 2, 4, 1, U27_3_4,
			45, 2, 3, 1, U27_3_4,
			46, 2, 4, 1, U27_3_4,
			47, 2, 3, 1, U27_3_4,
			48, 2, 4, 1, U27_3_4,
			49, 3, 5, 1, U27_4_5,

			// third gyrate cap
			2, 3, 3, 1, U27_4_5 + GeometryUtils.trivalentVertexAngle(3,10,4) - GeometryUtils.trivalentVertexAngle(4,10,3),
			51, 1, 4, 1, U27_3_4,
			52, 2, 3, 1, U27_3_4,
			53, 2, 4, 1, U27_3_4,
			54, 2, 3, 1, U27_3_4,
			55, 2, 4, 1, U27_3_4,
			56, 2, 3, 1, U27_3_4,
			57, 2, 4, 1, U27_3_4,
			58, 2, 3, 1, U27_3_4,
			59, 2, 4, 1, U27_3_4,
			60, 3, 5, 1, U27_4_5
		},

		new double[] // J76 - diminished rhombicosidodecahedron
		{
			52,
			5, 1,

			// ring around first pentagon
			0, 0, 4, 1, U27_4_5,
			0, 1, 4, 1, U27_4_5,
			0, 2, 4, 1, U27_4_5,
			0, 3, 4, 1, U27_4_5,
			0, 4, 4, 1, U27_4_5,
			1, 1, 3, 1, U27_3_4,
			2, 1, 3, 1, U27_3_4,
			3, 1, 3, 1, U27_3_4,
			4, 1, 3, 1, U27_3_4,
			5, 1, 3, 1, U27_3_4,

			// each adjacent pentagon and rings around them
			1, 2, 5, 1, U27_4_5,
			6, 2, 4, 1, U27_3_4,
			12, 2, 3, 1, U27_3_4,
			13, 2, 4, 1, U27_3_4,
			14, 2, 3, 1, U27_3_4,
			15, 2, 4, 1, U27_3_4,

			2, 2, 5, 1, U27_4_5,
			7, 2, 4, 1, U27_3_4,
			18, 2, 3, 1, U27_3_4,
			19, 2, 4, 1, U27_3_4,
			20, 2, 3, 1, U27_3_4,
			21, 2, 4, 1, U27_3_4,

			4, 2, 5, 1, U27_4_5,
			9, 2, 4, 1, U27_3_4,
			24, 2, 3, 1, U27_3_4,
			25, 2, 4, 1, U27_3_4,
			26, 2, 3, 1, U27_3_4,
			27, 2, 4, 1, U27_3_4,

			3, 2, 5, 1, U27_4_5,
			8, 2, 4, 1, U27_3_4,
			30, 2, 3, 1, U27_3_4,
			31, 2, 4, 1, U27_3_4,
			32, 2, 3, 1, U27_3_4,
			33, 2, 4, 1, U27_3_4,

			5, 2, 5, 1, U27_4_5,
			10, 2, 4, 1, U27_3_4,
			36, 2, 3, 1, U27_3_4,
			37, 2, 4, 1, U27_3_4,
			38, 2, 3, 1, U27_3_4,
			39, 2, 4, 1, U27_3_4,

			// next surrounding ring of pentagons and squares between them
			16, 1, 5, 1, U27_4_5,
			15, 1, 4, 1, U27_3_4,

			22, 1, 5, 1, U27_4_5,
			21, 1, 4, 1, U27_3_4,

			28, 1, 5, 1, U27_4_5,
			27, 1, 4, 1, U27_3_4,

			34, 1, 5, 1, U27_4_5,
			33, 1, 4, 1, U27_3_4,

			40, 1, 5, 1, U27_4_5,
			39, 1, 4, 1, U27_3_4,

			// the diminished pentagonal cupola
			41, 2, 10, 1, U27_4_5 - GeometryUtils.trivalentVertexAngle(4,10,3)
		},

		new double[] // J77 - paragyrate diminished rhombicosidodecahedron
		{
			52,

			// a 'strip' of pentagon-square-pentagon between the gyrated cap and the diminished cap
			5, 1,
			0, 1, 4, 1, U27_4_5,
			1, 2, 5, 1, U27_4_5,

			// a 'strip' of square-triangle-square-triangle-square between the gyrated cap and the diminished cap
			0, 3, 4, 1, U27_4_5,
			3, 1, 3, 1, U27_3_4,
			4, 1, 4, 1, U27_3_4,
			5, 2, 3, 1, U27_3_4,
			6, 2, 4, 1, U27_3_4,

			// eight further strips
			3, 2, 5, 1, U27_4_5,
			8, 1, 4, 1, U27_4_5,
			9, 2, 5, 1, U27_4_5,

			8, 3, 4, 1, U27_4_5,
			11, 1, 3, 1, U27_3_4,
			12, 1, 4, 1, U27_3_4,
			13, 2, 3, 1, U27_3_4,
			14, 2, 4, 1, U27_3_4,

			11, 2, 5, 1, U27_4_5,
			16, 1, 4, 1, U27_4_5,
			17, 2, 5, 1, U27_4_5,

			16, 3, 4, 1, U27_4_5,
			19, 1, 3, 1, U27_3_4,
			20, 1, 4, 1, U27_3_4,
			21, 2, 3, 1, U27_3_4,
			22, 2, 4, 1, U27_3_4,

			19, 2, 5, 1, U27_4_5,
			24, 1, 4, 1, U27_4_5,
			25, 2, 5, 1, U27_4_5,

			24, 3, 4, 1, U27_4_5,
			27, 1, 3, 1, U27_3_4,
			28, 1, 4, 1, U27_3_4,
			29, 2, 3, 1, U27_3_4,
			30, 2, 4, 1, U27_3_4,

			27, 2, 5, 1, U27_4_5,
			32, 1, 4, 1, U27_4_5,
			33, 2, 5, 1, U27_4_5,

			32, 3, 4, 1, U27_4_5,
			35, 1, 3, 1, U27_3_4,
			36, 1, 4, 1, U27_3_4,
			37, 2, 3, 1, U27_3_4,
			38, 2, 4, 1, U27_3_4,

			// a gyrated pentagonal cupola on one side
			0, 4, 3, 1, U27_4_5 + GeometryUtils.trivalentVertexAngle(3,10,4) - GeometryUtils.trivalentVertexAngle(4,10,3),
			40, 1, 4, 1, U27_3_4,
			41, 2, 3, 1, U27_3_4,
			42, 2, 4, 1, U27_3_4,
			43, 2, 3, 1, U27_3_4,
			44, 2, 4, 1, U27_3_4,
			45, 2, 3, 1, U27_3_4,
			46, 2, 4, 1, U27_3_4,
			47, 2, 3, 1, U27_3_4,
			48, 2, 4, 1, U27_3_4,
			49, 3, 5, 1, U27_4_5,

			// a diminishing decagon on the other side
			2, 3, 10, 1, U27_4_5 - GeometryUtils.trivalentVertexAngle(4,10,3)
		},

		new double[] // J78 - metagyrate diminished rhombicosidodecahedron
		{
			52,

			// pentagon-square-pentagon between the gyrate cap and the diminished cap
			5, 1,
			0, 0, 4, 1, U27_4_5,
			1, 2, 5, 1, U27_4_5,

			// square-triangle-square strip from gyrate cap to diminished cap
			2, 3, 4, 1, U27_4_5,
			3, 1, 3, 1, U27_3_4,
			4, 1, 4, 1, U27_3_4,

			// pentagon-square-pentagon strip from gyrate cap to diminished cap
			3, 2, 5, 1, U27_4_5,
			6, 1, 4, 1, U27_4_5,
			7, 2, 5, 1, U27_4_5,

			// square-triangle-square-triangle-square-triangle-square strip from gyrate cap to diminished cap
			6, 3, 4, 1, U27_4_5,
			9, 1, 3, 1, U27_3_4,
			10, 1, 4, 1, U27_3_4,
			11, 2, 3, 1, U27_3_4,
			12, 2, 4, 1, U27_3_4,
			13, 2, 3, 1, U27_3_4,
			14, 1, 4, 1, U27_3_4,

			// pentagon-square-pentagon-square-pentagon strip from gyrate cap to diminished cap
			9, 2, 5, 1, U27_4_5,
			16, 1, 4, 1, U27_4_5,
			17, 2, 5, 1, U27_4_5,
			18, 3, 4, 1, U27_4_5,
			19, 2, 5, 1, U27_4_5,

			// square-triangle-square-triangle-square between the end pentagons of the previous strip
			16, 2, 4, 1, U27_4_5,
			21, 1, 3, 1, U27_3_4,
			22, 2, 4, 1, U27_3_4,
			23, 2, 3, 1, U27_3_4,
			24, 2, 4, 1, U27_3_4,

			// pentagon between the end squares of the previous strip
			21, 2, 5, 1, U27_4_5,

			// square-triangle-square-triangle-square-triangle-square strip from gyrate cap to diminished cap
			16, 3, 4, 1, U27_4_5,
			27, 1, 3, 1, U27_3_4,
			28, 2, 4, 1, U27_3_4,
			29, 2, 3, 1, U27_3_4,
			30, 1, 4, 1, U27_3_4,
			31, 2, 3, 1, U27_3_4,
			32, 2, 4, 1, U27_3_4,

			// pentagon-square-pentagon strip from gyrate cap to diminished cap
			27, 2, 5, 1, U27_4_5,
			34, 2, 4, 1, U27_4_5,
			35, 2, 5, 1, U27_4_5,

			// square-triangle-square strip from gyrate cap to diminished cap
			34, 3, 4, 1, U27_4_5,
			37, 1, 3, 1, U27_3_4,
			38, 2, 4, 1, U27_3_4,

			// gyrated pentagonal cupola
			0, 1, 3, 1, U27_4_5 + GeometryUtils.trivalentVertexAngle(3,10,4) - GeometryUtils.trivalentVertexAngle(4,10,3),
			40, 1, 4, 1, U27_3_4,
			41, 2, 3, 1, U27_3_4,
			42, 2, 4, 1, U27_3_4,
			43, 2, 3, 1, U27_3_4,
			44, 2, 4, 1, U27_3_4,
			45, 2, 3, 1, U27_3_4,
			46, 2, 4, 1, U27_3_4,
			47, 2, 3, 1, U27_3_4,
			48, 2, 4, 1, U27_3_4,
			49, 3, 5, 1, U27_4_5,

			// diminished cap
			0, 4, 10, 1, U27_4_5 - GeometryUtils.trivalentVertexAngle(4,10,3)
		},

		new double[] // J79 - bigyrate diminished rhombicosidodecahedron
		{
			52,

			// ring of pentagons and squares around first gyrate cap
			5, 1,
			0, 2, 4, 1, U27_4_5,
			1, 2, 5, 1, U27_4_5,
			2, 2, 4, 1, U27_4_5,
			3, 2, 5, 1, U27_4_5,
			4, 2, 4, 1, U27_4_5,
			5, 2, 5, 1, U27_4_5,
			6, 2, 4, 1, U27_4_5,
			7, 2, 5, 1, U27_4_5,
			8, 2, 4, 1, U27_4_5,

			// ring of pentagons and squares around second gyrate cap
			0, 3, 4, 1, U27_4_5,
			10, 2, 5, 1, U27_4_5,
			11, 3, 4, 1, U27_4_5,
			12, 2, 5, 1, U27_4_5,
			13, 3, 4, 1, U27_4_5,
			14, 2, 5, 1, U27_4_5,
			15, 3, 4, 1, U27_4_5,

			// ring of pentagons and squares around diminished cap
			4, 3, 4, 1, U27_4_5,
			17, 2, 5, 1, U27_4_5,
			18, 3, 4, 1, U27_4_5,

			// triangle and square in small space between three caps
			1, 3, 3, 1, U27_3_4,
			20, 1, 4, 1, U27_3_4,

			// triangles and squares in larger Y-shaped space between three caps
			5, 3, 3, 1, U27_3_4,
			22, 1, 4, 1, U27_3_4,
			23, 2, 3, 1, U27_3_4,
			24, 1, 4, 1, U27_3_4,
			25, 2, 3, 1, U27_3_4,
			24, 2, 4, 1, U27_3_4,
			27, 2, 3, 1, U27_3_4,

			// first gyrate cap
			0, 1, 3, 1, U27_4_5 + GeometryUtils.trivalentVertexAngle(3,10,4) - GeometryUtils.trivalentVertexAngle(4,10,3),
			29, 1, 4, 1, U27_3_4,
			30, 2, 3, 1, U27_3_4,
			31, 2, 4, 1, U27_3_4,
			32, 2, 3, 1, U27_3_4,
			33, 2, 4, 1, U27_3_4,
			34, 2, 3, 1, U27_3_4,
			35, 2, 4, 1, U27_3_4,
			36, 2, 3, 1, U27_3_4,
			37, 2, 4, 1, U27_3_4,
			38, 3, 5, 1, U27_4_5,

			// second gyrate cap
			0, 4, 3, 1, U27_4_5 + GeometryUtils.trivalentVertexAngle(3,10,4) - GeometryUtils.trivalentVertexAngle(4,10,3),
			40, 1, 4, 1, U27_3_4,
			41, 2, 3, 1, U27_3_4,
			42, 2, 4, 1, U27_3_4,
			43, 2, 3, 1, U27_3_4,
			44, 2, 4, 1, U27_3_4,
			45, 2, 3, 1, U27_3_4,
			46, 2, 4, 1, U27_3_4,
			47, 2, 3, 1, U27_3_4,
			48, 2, 4, 1, U27_3_4,
			49, 3, 5, 1, U27_4_5,

			// diminished cap
			2, 3, 10, 1, U27_4_5 - GeometryUtils.trivalentVertexAngle(4,10,3)
		},

		new double[] // J80 - parabidiminished rhombicosidodecahedron
		{
			42,

			// a 'strip' of pentagon-square-pentagon between the two diminished caps
			5, 1,
			0, 1, 4, 1, U27_4_5,
			1, 2, 5, 1, U27_4_5,

			// a 'strip' of square-triangle-square-triangle-square between the two diminished caps
			0, 3, 4, 1, U27_4_5,
			3, 1, 3, 1, U27_3_4,
			4, 1, 4, 1, U27_3_4,
			5, 2, 3, 1, U27_3_4,
			6, 2, 4, 1, U27_3_4,

			// eight further strips
			3, 2, 5, 1, U27_4_5,
			8, 1, 4, 1, U27_4_5,
			9, 2, 5, 1, U27_4_5,

			8, 3, 4, 1, U27_4_5,
			11, 1, 3, 1, U27_3_4,
			12, 1, 4, 1, U27_3_4,
			13, 2, 3, 1, U27_3_4,
			14, 2, 4, 1, U27_3_4,

			11, 2, 5, 1, U27_4_5,
			16, 1, 4, 1, U27_4_5,
			17, 2, 5, 1, U27_4_5,

			16, 3, 4, 1, U27_4_5,
			19, 1, 3, 1, U27_3_4,
			20, 1, 4, 1, U27_3_4,
			21, 2, 3, 1, U27_3_4,
			22, 2, 4, 1, U27_3_4,

			19, 2, 5, 1, U27_4_5,
			24, 1, 4, 1, U27_4_5,
			25, 2, 5, 1, U27_4_5,

			24, 3, 4, 1, U27_4_5,
			27, 1, 3, 1, U27_3_4,
			28, 1, 4, 1, U27_3_4,
			29, 2, 3, 1, U27_3_4,
			30, 2, 4, 1, U27_3_4,

			27, 2, 5, 1, U27_4_5,
			32, 1, 4, 1, U27_4_5,
			33, 2, 5, 1, U27_4_5,

			32, 3, 4, 1, U27_4_5,
			35, 1, 3, 1, U27_3_4,
			36, 1, 4, 1, U27_3_4,
			37, 2, 3, 1, U27_3_4,
			38, 2, 4, 1, U27_3_4,

			// one diminished cap
			0, 4, 10, 1, U27_4_5 - GeometryUtils.trivalentVertexAngle(4,10,3),

			// the other diminished cap
			2, 3, 10, 1, U27_4_5 - GeometryUtils.trivalentVertexAngle(4,10,3)
		},

		new double[] // J81 - metabidiminished rhombicosidodecahedron
		{
			42,

			// pentagon-square-pentagon between the diminished caps
			5, 1,
			0, 0, 4, 1, U27_4_5,
			1, 2, 5, 1, U27_4_5,

			// square-triangle-square strip from cap to cap
			2, 3, 4, 1, U27_4_5,
			3, 1, 3, 1, U27_3_4,
			4, 1, 4, 1, U27_3_4,

			// pentagon-square-pentagon strip from cap to cap
			3, 2, 5, 1, U27_4_5,
			6, 1, 4, 1, U27_4_5,
			7, 2, 5, 1, U27_4_5,

			// square-triangle-square-triangle-square-triangle-square strip from cap to cap
			6, 3, 4, 1, U27_4_5,
			9, 1, 3, 1, U27_3_4,
			10, 1, 4, 1, U27_3_4,
			11, 2, 3, 1, U27_3_4,
			12, 2, 4, 1, U27_3_4,
			13, 2, 3, 1, U27_3_4,
			14, 1, 4, 1, U27_3_4,

			// pentagon-square-pentagon-square-pentagon strip from cap to cap
			9, 2, 5, 1, U27_4_5,
			16, 1, 4, 1, U27_4_5,
			17, 2, 5, 1, U27_4_5,
			18, 3, 4, 1, U27_4_5,
			19, 2, 5, 1, U27_4_5,

			// square-triangle-square-triangle-square between the end pentagons of the previous strip
			16, 2, 4, 1, U27_4_5,
			21, 1, 3, 1, U27_3_4,
			22, 2, 4, 1, U27_3_4,
			23, 2, 3, 1, U27_3_4,
			24, 2, 4, 1, U27_3_4,

			// pentagon between the end squares of the previous strip
			21, 2, 5, 1, U27_4_5,

			// square-triangle-square-triangle-square-triangle-square strip from cap to cap
			16, 3, 4, 1, U27_4_5,
			27, 1, 3, 1, U27_3_4,
			28, 2, 4, 1, U27_3_4,
			29, 2, 3, 1, U27_3_4,
			30, 1, 4, 1, U27_3_4,
			31, 2, 3, 1, U27_3_4,
			32, 2, 4, 1, U27_3_4,

			// pentagon-square-pentagon strip from cap to cap
			27, 2, 5, 1, U27_4_5,
			34, 2, 4, 1, U27_4_5,
			35, 2, 5, 1, U27_4_5,

			// square-triangle-square strip from cap to cap
			34, 3, 4, 1, U27_4_5,
			37, 1, 3, 1, U27_3_4,
			38, 2, 4, 1, U27_3_4,

			// one diminished cap
			0, 1, 10, 1, U27_4_5 - GeometryUtils.trivalentVertexAngle(4,10,3),

			// the other diminished cap
			0, 4, 10, 1, U27_4_5 - GeometryUtils.trivalentVertexAngle(4,10,3)
		},

		new double[] // J82 - gyrate bidiminished rhombicosidodecahedron
		{
			42,

			// ring of pentagons and squares around gyrate cap
			5, 1,
			0, 2, 4, 1, U27_4_5,
			1, 2, 5, 1, U27_4_5,
			2, 2, 4, 1, U27_4_5,
			3, 2, 5, 1, U27_4_5,
			4, 2, 4, 1, U27_4_5,
			5, 2, 5, 1, U27_4_5,
			6, 2, 4, 1, U27_4_5,
			7, 2, 5, 1, U27_4_5,
			8, 2, 4, 1, U27_4_5,

			// ring of pentagons and squares around first diminished cap
			0, 3, 4, 1, U27_4_5,
			10, 2, 5, 1, U27_4_5,
			11, 3, 4, 1, U27_4_5,
			12, 2, 5, 1, U27_4_5,
			13, 3, 4, 1, U27_4_5,
			14, 2, 5, 1, U27_4_5,
			15, 3, 4, 1, U27_4_5,

			// ring of pentagons and squares around second diminished cap
			4, 3, 4, 1, U27_4_5,
			17, 2, 5, 1, U27_4_5,
			18, 3, 4, 1, U27_4_5,

			// triangle and square in small space between three caps
			1, 3, 3, 1, U27_3_4,
			20, 1, 4, 1, U27_3_4,

			// triangles and squares in larger Y-shaped space between three caps
			5, 3, 3, 1, U27_3_4,
			22, 1, 4, 1, U27_3_4,
			23, 2, 3, 1, U27_3_4,
			24, 1, 4, 1, U27_3_4,
			25, 2, 3, 1, U27_3_4,
			24, 2, 4, 1, U27_3_4,
			27, 2, 3, 1, U27_3_4,

			// gyrate cap
			0, 1, 3, 1, U27_4_5 + GeometryUtils.trivalentVertexAngle(3,10,4) - GeometryUtils.trivalentVertexAngle(4,10,3),
			29, 1, 4, 1, U27_3_4,
			30, 2, 3, 1, U27_3_4,
			31, 2, 4, 1, U27_3_4,
			32, 2, 3, 1, U27_3_4,
			33, 2, 4, 1, U27_3_4,
			34, 2, 3, 1, U27_3_4,
			35, 2, 4, 1, U27_3_4,
			36, 2, 3, 1, U27_3_4,
			37, 2, 4, 1, U27_3_4,
			38, 3, 5, 1, U27_4_5,

			// first diminished cap
			0, 4, 10, 1, U27_4_5 - GeometryUtils.trivalentVertexAngle(4,10,3),

			// second diminished cap
			2, 3, 10, 1, U27_4_5 - GeometryUtils.trivalentVertexAngle(4,10,3)
		},

		new double[] // J83 - tridiminished rhombicosidodecahedron
		{
			32,

			// ring of pentagons and squares around first diminished cap
			5, 1,
			0, 2, 4, 1, U27_4_5,
			1, 2, 5, 1, U27_4_5,
			2, 2, 4, 1, U27_4_5,
			3, 2, 5, 1, U27_4_5,
			4, 2, 4, 1, U27_4_5,
			5, 2, 5, 1, U27_4_5,
			6, 2, 4, 1, U27_4_5,
			7, 2, 5, 1, U27_4_5,
			8, 2, 4, 1, U27_4_5,

			// ring of pentagons and squares around second diminished cap
			0, 3, 4, 1, U27_4_5,
			10, 2, 5, 1, U27_4_5,
			11, 3, 4, 1, U27_4_5,
			12, 2, 5, 1, U27_4_5,
			13, 3, 4, 1, U27_4_5,
			14, 2, 5, 1, U27_4_5,
			15, 3, 4, 1, U27_4_5,

			// ring of pentagons and squares around third diminished cap
			4, 3, 4, 1, U27_4_5,
			17, 2, 5, 1, U27_4_5,
			18, 3, 4, 1, U27_4_5,

			// triangle and square in small space between three diminished caps
			1, 3, 3, 1, U27_3_4,
			20, 1, 4, 1, U27_3_4,

			// triangles and squares in larger Y-shaped space between three diminished caps
			5, 3, 3, 1, U27_3_4,
			22, 1, 4, 1, U27_3_4,
			23, 2, 3, 1, U27_3_4,
			24, 1, 4, 1, U27_3_4,
			25, 2, 3, 1, U27_3_4,
			24, 2, 4, 1, U27_3_4,
			27, 2, 3, 1, U27_3_4,

			// first diminished cap
			0, 1, 10, 1, U27_4_5 - GeometryUtils.trivalentVertexAngle(4,10,3),

			// second diminished cap
			0, 4, 10, 1, U27_4_5 - GeometryUtils.trivalentVertexAngle(4,10,3),

			// third diminished cap
			2, 3, 10, 1, U27_4_5 - GeometryUtils.trivalentVertexAngle(4,10,3)
		},

		new double[] // J84 - snub disphenoid
		{
			8,
			1,0,0,  J84_XB,J84_YB,0,  0,J84_YC,J84_XB,  0,J84_YB+J84_YC,1,
			-1,0,0,  -J84_XB,J84_YB,0,
			0,J84_YC,-J84_XB,  0,J84_YB+J84_YC,-1,

			12,

			// bottom sphenoid
			3, 0,2,4,
			3, 0,4,6,

			// ring of triangles between sphenoids
			3, 0,1,2,
			3, 2,1,3,
			3, 2,3,5,
			3, 2,5,4,
			3, 4,5,6,
			3, 6,5,7,
			3, 1,6,7,
			3, 1,0,6,

			// bottom sphenoid
			3, 3,1,7,
			3, 5,3,7
		},

		new double[] // J85 - snub square antiprism
		{
			16,
			1,1,0,  J85_XB,0,J85_ZB,  J85_XC,J85_XC,J85_ZC,
			-1,1,0,  0,J85_XB,J85_ZB,  -J85_XC,J85_XC,J85_ZC,
			-1,-1,0,  -J85_XB,0,J85_ZB,  -J85_XC,-J85_XC,J85_ZC,
			1,-1,0,  0,-J85_XB,J85_ZB,  J85_XC,-J85_XC,J85_ZC,
			Math.sqrt(2),0,J85_ZB+J85_ZC,  0,Math.sqrt(2),J85_ZB+J85_ZC,  -Math.sqrt(2),0,J85_ZB+J85_ZC,  0,-Math.sqrt(2),J85_ZB+J85_ZC,

			26,

			// bottom square
			4, 0,9,6,3,

			// bottom ring of triangles
			3, 1,9,0,
			3, 0,2,1,
			3, 0,4,2,
			3, 3,4,0,
			3, 3,5,4,
			3, 3,7,5,
			3, 6,7,3,
			3, 6,8,7,
			3, 6,10,8,
			3, 9,10,6,
			3, 9,11,10,
			3, 1,11,9,

			// top ring of triangles
			3, 2,12,1,
			3, 2,13,12,
			3, 4,13,2,
			3, 5,13,4,
			3, 5,14,13,
			3, 7,14,5,
			3, 8,14,7,
			3, 8,15,14,
			3, 10,15,8,
			3, 11,15,10,
			3, 11,12,15,
			3, 1,12,11,

			// top square
			4, 12,13,14,15
		},

		new double[] // J86 - sphenocorona
		{
			10,
			0,0,1,  J86_XB,J86_YB,1,  0,J86_YC,J86_ZC,  1,J86_YD,0,
			-J86_XB,J86_YB,1,  -1,J86_YD,0,
			0,0,-1,  J86_XB,J86_YB,-1,  0,J86_YC,-J86_ZC,  -J86_XB,J86_YB,-1,

			14,

			// the two squares forming a 'V'
			4, 1,0,6,7,
			4, 0,4,9,6,

			// two triangles between the squares on one side
			3, 0,1,2,
			3, 0,2,4,

			// three triangles above these two
			3, 1,3,2,
			3, 2,3,5,
			3, 2,5,4,

			// two triangles between the squares on the other side
			3, 7,6,8,
			3, 6,9,8,

			// three triangles above these two
			3, 3,7,8,
			3, 5,3,8,
			3, 5,8,9,

			// a triangle above each end of the 'V'
			3, 3,1,7,
			3, 4,5,9
		},

		new double[] // J87 - augmented sphenocorona
		{
			11,
			0,0,1,  J86_XB,J86_YB,1,  0,J86_YC,J86_ZC,  1,J86_YD,0,
			-J86_XB,J86_YB,1,  -1,J86_YD,0,
			0,0,-1,  J86_XB,J86_YB,-1,  0,J86_YC,-J86_ZC,  -J86_XB,J86_YB,-1,
			J86_XB/2+J86_YB/Math.sqrt(2),J86_YB/2-J86_XB/Math.sqrt(2),0,

			17,

			// the square
			4, 0,4,9,6,

			// a triangular pyramid where the other square of J86 is
			3, 1,0,10,
			3, 7,1,10,
			3, 6,7,10,
			3, 0,6,10,

			// two triangles between the squares on one side
			3, 0,1,2,
			3, 0,2,4,

			// three triangles above these two
			3, 1,3,2,
			3, 2,3,5,
			3, 2,5,4,

			// two triangles between the squares on the other side
			3, 7,6,8,
			3, 6,9,8,

			// three triangles above these two
			3, 3,7,8,
			3, 5,3,8,
			3, 5,8,9,

			// a triangle above each end of the 'V'
			3, 3,1,7,
			3, 4,5,9
		},

		new double[] // J88 - sphenomegacorona
		{
			12,
			0,0,1, J88_XB,J88_YB,1,  0,J88_YC,J88_ZC,  0,J88_YD,J88_ZD,  1,J88_YE,0,
			-J88_XB,J88_YB,1,  -1,J88_YE,0,
			0,0,-1,  J88_XB,J88_YB,-1,  0,J88_YC,-J88_ZC,  0,J88_YD,-J88_ZD,
			-J88_XB,J88_YB,-1,

			18,

			// the two squares forming a 'V'
			4, 1,0,7,8,
			4, 0,5,11,7,

			// two triangles between the squares
			3, 0,1,2,
			3, 0,2,5,

			// two triangles above the two triangles
			3, 1,3,2,
			3, 2,3,5,

			// two triangles between the squares on the other side
			3, 7,9,8,
			3, 7,11,9,

			// two triangles above the two triangles
			3, 8,9,10,
			3, 10,9,11,

			// three triangles above one end of the 'V'
			3, 1,4,3,
			3, 1,8,4,
			3, 4,8,10,

			// three triangles above the other end of the 'V'
			3, 3,6,5,
			3, 5,6,11,
			3, 6,10,11,

			// two triangles at the top
			3, 3,4,6,
			3, 6,4,10
		},

		new double[] // J89 - hebesphenomegacorona
		{
			14,
			1,0,1,  J89_XB,J89_YB,1,  0,J89_YC,J89_ZC,  0,J89_YD,J89_ZD,  1,J89_YE,0,
			-1,0,1,  -J89_XB,J89_YB,1,  -1,J89_YE,0,
			1,0,-1,  J89_XB,J89_YB,-1,  0,J89_YC,-J89_ZC,  0,J89_YD,-J89_ZD,
			-1,0,-1,  -J89_XB,J89_YB,-1,

			21,

			// the bottom square
			4, 0,5,12,8,

			// the two side squares
			4, 0,8,9,1,
			4, 5,6,13,12,

			// three triangles between the 'U' of squares
			3, 0,1,2,
			3, 0,2,5,
			3, 5,2,6,

			// two triangles above these three
			3, 2,1,3,
			3, 2,3,6,

			// three triangles on the other side of the 'U'
			3, 8,10,9,
			3, 8,12,10,
			3, 12,13,10,

			// two triangles above these three
			3, 10,11,9,
			3, 10,13,11,

			// three triangles above one end of the 'U'
			3, 1,4,3,
			3, 1,9,4,
			3, 9,11,4,

			// three triangles above the other end of the 'U'
			3, 6,3,7,
			3, 6,7,13,
			3, 13,7,11,

			// two triangles at the top
			3, 3,4,7,
			3, 7,4,11
		},

		new double[] // J90 - disphenocingulum
		{
			16,
			0,0,1,  J90_XB,J90_YB,1,  0,J90_YC,J90_ZC,  1,J90_YD,J90_XB,  J90_ZC,J90_YD+J90_YB-J90_YC,0,  1,J90_YD+J90_YB,0,
			-J90_XB,J90_YB,1,  -1,J90_YD,J90_XB,  -J90_ZC,J90_YD+J90_YB-J90_YC,0,  -1,J90_YD+J90_YB,0,
			0,0,-1,  J90_XB,J90_YB,-1,  0,J90_YC,-J90_ZC,  1,J90_YD,-J90_XB,
			-J90_XB,J90_YB,-1,  -1,J90_YD,-J90_XB,

			24,

			// the bottom sphenum
			4, 1,0,10,11,
			4, 0,6,14,10,

			// two triangles between the squares on one side of the bottom sphenum
			3, 0,1,2,
			3, 0,2,6,

			// two triangles between the squares on the other side of the bottom sphenum
			3, 10,12,11,
			3, 10,14,12,

			// ring of triangles around the centre
			3, 1,4,3,
			3, 1,3,2,
			3, 2,3,7,
			3, 2,7,6,
			3, 6,7,8,
			3, 6,8,14,
			3, 8,15,14,
			3, 12,14,15,
			3, 12,15,13,
			3, 11,12,13,
			3, 4,11,13,
			3, 4,1,11,

			// the top sphenum
			4, 3,5,9,7,
			4, 9,5,13,15,

			// two triangles between the squares on one side of the top sphenum
			3, 3,4,5,
			3, 5,4,13,

			// two triangles between the squares on the other side of the top sphenum
			3, 7,9,8,
			3, 8,9,15
		},

		new double[] // J91 - bilunabirotunda
		{
			14,

			// two adjacent pentagons
			5, 1,
			0, 0, 5, 1, GeometryUtils.trivalentVertexAngle(5,5,3),

			// triangles either side of the pentagons
			0, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,5,5),
			0, 4, 3, 1, GeometryUtils.trivalentVertexAngle(3,5,5),

			// four triangles above the two pentagons
			0, 2, 3, 1, U24_3_5,
			0, 3, 3, 1, U24_3_5,
			1, 2, 3, 1, U24_3_5,
			1, 3, 3, 1, U24_3_5,

			// squares between the triangles
			4, 1, 4, 1, GeometryUtils.trivalentVertexAngle(3,4,5),
			5, 2, 4, 1, GeometryUtils.trivalentVertexAngle(3,4,5),

			// opposite pair of pentagons
			4, 2, 5, 1, U24_3_5,
			6, 2, 5, 1, U24_3_5,

			// triangles either side of opposite pair of pentagons
			10, 1, 3, 1, GeometryUtils.trivalentVertexAngle(3,5,5),
			10, 3, 3, 1, GeometryUtils.trivalentVertexAngle(3,5,5)
		},

		new double[] // J92 - triangular hebesphenorotunda
		{
			20,

			// central triangle
			3, 1,

			// three surrounding pentagons
			0, 0, 5, 1, U24_3_5,
			0, 1, 5, 1, U24_3_5,
			0, 2, 5, 1, U24_3_5,

			// triangles between the pentagons
			1, 1, 3, 1, U24_3_5,
			2, 1, 3, 1, U24_3_5,
			3, 1, 3, 1, U24_3_5,

			// triangles outside the pentagons
			1, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(5,1),
			1, 3, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(5,1),
			2, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(5,1),
			2, 3, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(5,1),
			3, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(5,1),
			3, 3, 3, 1, GeometryUtils.uniformAntiprismTriangleEndDihedralAngle(5,1),

			// triangles between triangles
			7, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(5,1),
			9, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(5,1),
			11, 2, 3, 1, GeometryUtils.uniformAntiprismTriangleTriangleDihedralAngle(5,1),

			7, 1, 4, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			9, 1, 4, 1, GeometryUtils.trivalentVertexAngle(3,4,10),
			11, 1, 4, 1, GeometryUtils.trivalentVertexAngle(3,4,10),

			16, 3, 6, 1, GeometryUtils.trivalentVertexAngle(4,10,3)+GeometryUtils.trivalentVertexAngle(6,10,3)
		}
	};

	// Johnson solids
	public static Polyhedron Johnson(short index, double edgeLength, Point3 centre, double xRotation, double yRotation)
	{
		if (index < 1 || index > 92)
			throw new IllegalArgumentException("Invalid Johnson Solid number: " + index);

		Polyhedron h;
		if (index >= 84 && index <= 90)
			h = Polyhedron.Coordinates(JohnsonData[index - 1], edgeLength, centre, xRotation, yRotation);
		else
			h = Polyhedron.FacesAngles(JohnsonData[index - 1], edgeLength, centre, xRotation, yRotation);

		//CopyFrom(H);
		//DrawingType = Polyhedron.t_drawingType.CONVEX_ORIENTED;
		//name = JohnsonNames[index - 1];
		//shortName = "JN" + index.ToString("D2");

		return h;
	}
}
