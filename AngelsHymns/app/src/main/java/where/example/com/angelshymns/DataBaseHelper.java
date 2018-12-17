package where.example.com.angelshymns;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Antoun on 4/8/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String ENCODING_SETTING = "PRAGMA encoding ='windows-1256'";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME="Hymns.db";
    private Context c;
    private static DataBaseHelper instance = null;

    public DataBaseHelper(Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
        this.c=c;
    }

    public static DataBaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DataBaseHelper(context);
        }
        return instance;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        if (!db.isReadOnly()) {
            db.execSQL(ENCODING_SETTING);
        }
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + Contract.HymnEntry.TABLE_NAME+ " (" +
                Contract.HymnEntry.COLUMN_ID + " INTEGER PRIMARY KEY, "+
                Contract.HymnEntry.NAME + " TEXT NOT NULL, "+
                Contract.HymnEntry.CONTENT+" TEXT NOT NULL, "+
                Contract.HymnEntry.CONTENT_COPTIC+" TEXT NOT NULL "+
                ");"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public String addHymns()
    {

        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(Contract.HymnEntry.COLUMN_ID,"11");
        values.put(Contract.HymnEntry.NAME,"مرد انجيل رفع بخور عشية");
        values.put(Contract.HymnEntry.CONTENT,"لأنه مبارك الاب والابن والروح القدس الثالوث الكامل نسجد له ونمجده .");
        values.put(Contract.HymnEntry.CONTENT_COPTIC,"جى اف اسمارووت انجى افيوت نيم ابشيري. نيم بي ابنفما اثؤواب . تى ترياس اتجيك ايفول . تين اؤؤوشت امموس تي نتي او اوناس .");

        long rowInserted= db.insert(Contract.HymnEntry.TABLE_NAME,null,values);

        values.put(Contract.HymnEntry.COLUMN_ID,"12");
        values.put(Contract.HymnEntry.NAME,"لحن سوتيس امين");
        values.put(Contract.HymnEntry.CONTENT,"خلصت حقاً و مع روحك  . ");
        values.put(Contract.HymnEntry.CONTENT_COPTIC,"سوتيس امين كى طو ابنفماتي سو . ");

        long rowInserted1= db.insert(Contract.HymnEntry.TABLE_NAME,null,values);

        values.put(Contract.HymnEntry.COLUMN_ID,"13");
        values.put(Contract.HymnEntry.NAME,"مرد المجمع الباسيلي");
        values.put(Contract.HymnEntry.CONTENT,"بركتهم المقدسة تكون معنا آمين . المجد لك يا رب . يا رب ارحم ، يا رب ارحم ، يا رب باركنا يا رب نيحهم امين.");
        values.put(Contract.HymnEntry.CONTENT_COPTIC,"إري بو اسمو اثؤواف شوبي نيمان آمين ذو كساسي كيريي كيريي ليسون . كيريي ليسون ، كيريي افلوجيسون كيريي آنا بافصون آمين.");

        long rowInserted2= db.insert(Contract.HymnEntry.TABLE_NAME,null,values);

        values.put(Contract.HymnEntry.COLUMN_ID,"14");
        values.put(Contract.HymnEntry.NAME,"مرد انجيل القداس السنوى");
        values.put(Contract.HymnEntry.CONTENT,"طوباهم بالحقيقة قديسي هذا اليوم كل واحد و واحد بأسمه احباء المسيح  . \n" +
                "اشفعي فينا يا سيدتنا كلنا السيدة العذراء والدة الأله مريم ام مخلصنا ليغفر لنا خطايانا .\n" +
                "مبارك الأب و الأبن والروح القدس الثالوث الكامل نسجد له و نمجده . \n");
                values.put(Contract.HymnEntry.CONTENT_COPTIC,"اؤو نياتو خين او ميثمي ني اثؤواب انتى باي ايهوؤو بي اواي بي اواي كاطا بيف ران ني مين راتي انتى بى اخريستوس  .  \n" +
                        "اري ابريسفافين اى ايهري ايجون اوتين تشويس انيب تيرن تي ثيؤطوكوس ماريا اثماف امبين سوتير: انتيف كانين نوفي نان ايفول .  \n" +
                        "جى اف اسمارووت انجى افيوت نيم ابشيري : نيم بي ابنفما اثؤواب: تى ترياس اتجيك ايفول تين اؤؤوشت امموس تين تي أو اووناس  . \n");

        long rowInserted3= db.insert(Contract.HymnEntry.TABLE_NAME,null,values);

        values.put(Contract.HymnEntry.COLUMN_ID,"15");
        values.put(Contract.HymnEntry.NAME," مرد التوزيع لسبوت و احاد الصوم المقدس ");
        values.put(Contract.HymnEntry.CONTENT,"يسوع المسيح صام عنا أربعين يوما و أربعين ليلة . ");
        values.put(Contract.HymnEntry.CONTENT_COPTIC,"الليلويا (4) : آ ايسوس بخرستوس ايرنيستيفين ااهري ايجون ان اهمي ان ايهوأوو نيم اهمي ان ايجوره.");

        long rowInserted4= db.insert(Contract.HymnEntry.TABLE_NAME,null,values);

        values.put(Contract.HymnEntry.COLUMN_ID,"16");
        values.put(Contract.HymnEntry.NAME," مرد انجيل ايام الصوم المقدس ");
        values.put(Contract.HymnEntry.CONTENT,"سلام الله الذي يفوق كل عقل يحفظ قلوبكم في المسيح يسوع ربنا  . \n" +
                " اخطأت اخطأت ياربي يسوع المسيح اغفر لي لأنه ليس عبد بلا خطية ولا سيد بلا غفران  . \n" +
                " ابانا الذي في السموات ليتقدس اسمك ليأتي ملكوتك لأن لك المجد الى الأبد  . \n" +
                " لأنه مبارك ألاب والأبن و الروح القدس الثالوث الكامل نسجد له ونمجده  . \n ");
        values.put(Contract.HymnEntry.CONTENT_COPTIC,"تي هيريني انتى افنوتي سى ايتتشوسي اينوس نيفين اس ايه اريه انيت انهيت خين بيخرستوس ايسوس بين تشويس . \n" +
                " اي ايرنوفي اي اير نوفي باتشويس ايسوس كو ني ايفول جى اممون فوك ان ات اير نوفي او ذي اممون ايتشويس ان اتكو ايفول  . \n" +
                " جى بينيوت اتخين ني فيووي ماريف طوفو انجى بيكران ماريسئ انجى تيك ميت اورو جي فوك بى بي اوو شا ني اينيه  . \n" +
                " جي افسمارؤوت انجى افيوت نيم ابشيري نيم بي بنفما اثوواب تترياس ايتجيك ايفول تين اؤوشت امموس تين تي اوؤناس  . \n");
        long rowInserted5= db.insert(Contract.HymnEntry.TABLE_NAME,null,values);
        // oula we tanya

        values.put(Contract.HymnEntry.COLUMN_ID,"111");
        values.put(Contract.HymnEntry.NAME," الليلويا جى افميفى ");
        values.put(Contract.HymnEntry.CONTENT,"هلليلويا ان فكر الأنسان يعترف لك يا رب ، وبقية الفكر تُعيَد لك الذبائح والتقدمات اقبلها اليك هلليلويا.");
        values.put(Contract.HymnEntry.CONTENT_COPTIC,"الليلويا جى افميفي ان اورومي إيف اى اوؤنه ناك ايفول ابتشويس اووه ابسوجب انتى او ميفي إيف اى ارشيناك ني ثيسيا ني ابروس فورا شوبو ايروك الليلويا.");

        long rowInserted6= db.insert(Contract.HymnEntry.TABLE_NAME,null,values);

        values.put(Contract.HymnEntry.COLUMN_ID,"112");
        values.put(Contract.HymnEntry.NAME," الليلويا فاى بيبى ");
        values.put(Contract.HymnEntry.CONTENT,"هلليلويا هذا هو اليوم الذي صنعه الرب فلنفرح و نبتهج فيه يا رب خلصنا يا رب سهل سبلنا مبارك الأتي بأسم الرب هلليلويا.");
        values.put(Contract.HymnEntry.CONTENT_COPTIC,"الليلويا فاي بى بي ايهؤؤو ايطاابتشويس ثاميوف مارين ثيليل انتين اونوف اممون انخيتف او ابتشويس ايك اى ناهمين او ابتشويس ايك اى سوتين نين مويت اف اسمارؤؤت انجى في اثنيو خين افرانام ابتشويس الليلويا.");

        long rowInserted7= db.insert(Contract.HymnEntry.TABLE_NAME,null,values);

        values.put(Contract.HymnEntry.COLUMN_ID,"113");
        values.put(Contract.HymnEntry.NAME," الليلويا اى ايه اى ايخون ");
        values.put(Contract.HymnEntry.CONTENT,"الليلويا أدخل إلى مذبح الله أمام الله الذي يبهج شبابي اعترف لك يا الله الهي بالقيثارة الليلويا اذكر يا رب داود وكل دعته الليلويا.");
        values.put(Contract.HymnEntry.CONTENT_COPTIC,"الليلويا اى إيه إي ايخون شا بيما إن ايرشوؤشي انتى افنوتي: ناهرين ابهو ام افنوتي فيئتافتي ام ابونوف انتى تاميت آلو: تي نا أوأونه ناك ايفول افنوتي بانوتي خين أو كيثارا: أري افميفئي ابتشويس إن دافيد نيم تيف ميت ريم رافش تيرس الليلويا");

        long rowInserted8= db.insert(Contract.HymnEntry.TABLE_NAME,null,values);

        values.put(Contract.HymnEntry.COLUMN_ID,"114");
        values.put(Contract.HymnEntry.NAME,"ارباع الناقوس لسبوت و احاد الصوم المقدس");
        values.put(Contract.HymnEntry.CONTENT,"ربنا يسوع المسيح صام عنا أربعين يوما و أربعين ليلة لكى يخلصنا من خطايانا . \n" +
                "و نحن أيضاً فلنصم بطهارة و بر و نصلى صارخين قائلين \n" +
                "أبانا الذى فى السموات ليتقدس اسمك ليأت ملكوتك لأن لك المجد إلى الأبد \n");
        values.put(Contract.HymnEntry.CONTENT_COPTIC,"ا بين تشويس إيسوس بخرستوس: إرنيستيفين إى إهرى إيجون: إن إهمى إن إيهواوو نيم إهمى إن إيجوره: شا إنتيف سوتون خين نين نوفى . \n" +
                "أنون هون مارين إرنيستيﭭين: خين أو توفو نيم أووميثمى أووه إنتين إبروس إف شيستى:إنؤش إيفول إنجو إمموس . \n" +
                "جى بين يوت إتخين نيفيؤى: ماريف توفو إنجى بيك ران: ماريسئى إنجى تيك ميت أورو جى فوك بى بى اواوو شا إينيه . \n");

        long rowInserted9= db.insert(Contract.HymnEntry.TABLE_NAME,null,values);

        values.put(Contract.HymnEntry.COLUMN_ID,"115");
        values.put(Contract.HymnEntry.NAME,"ذكصولوجية مارمرقس الاولى");
        values.put(Contract.HymnEntry.CONTENT,"يا مرقس الرسول، و الإنجيلي، الشاهد لآلام، الإله الوحيد الجنس  . \n" +
                "أتيت و أنرت لنا، بإنجيلك، وعلمتنا الآب و الابن، و الروح القدس. \n" +
                "و أخرجتنا من الظلمة، إلي النور الحقيقي، و أطعمتنا خبز الحياة، الذي نزل من السماء. \n" +
                "تباركت بك، كل قبائل الأرض، وأقوالك بلغت، إلي أقطار المسكونة. \n" +
                "السلام لك أيها الشهيد، السلام للإنجيلي، السلام للرسول، مرقس ناظر الإله. \n" +
                "اطلب من الرب عنا، يا ناظر الإله الإنجيلي، مرقس الرسول، ليغفر لنا خطايانا\n");
        values.put(Contract.HymnEntry.CONTENT_COPTIC,"ماركوس بي أبوسطولوس : أووه بي إف أنجيليستيس : بي ميثريه خا ني إمكافه : إنتيه بي مونوجينيس إن نووتي  . \n" +
                "أك إي أك إر أووأويني إرون : هيتين بيك إف أنجيليون : أك إتسافون إم إفيوت نيم إبشيري : نيم بي بنيفما إثؤواب. \n" +
                "أك إنتين إفول خين إبكاكي إخوون إبي أووأويني إممي : أك تيم مون إم بي أويك إنتيه إب أونخ : إطاف إي إبيسيت إفول خين إتفيه. \n" +
                "أف تشي إسموو إن إخري إنخيتك : إنجيه ني فيلي تيروو إنتيه إبكاهي : أووه نيك صاجي أف فوه : شا أفريجس إنتي إي كووميني. \n" +
                "شيريه ناك أو بي مارتيروس : شيري بي إف أنجيليستيس : شيريه بي أبوسطولوس : أفا ماركوس بي ثيئوريموس. \n" +
                "طوبه إم إبتشويس إ إهري إجون : أو بي ثيئوريموس إن إف أنجيليستيس أفا ماركوس بي أبوسطولوس : إنتيف كا نين نوفي نان إفول. \n");

        long rowInserted10= db.insert(Contract.HymnEntry.TABLE_NAME,null,values);

        values.put(Contract.HymnEntry.COLUMN_ID,"116");
        values.put(Contract.HymnEntry.NAME,"ختام التسبحة");
        values.put(Contract.HymnEntry.CONTENT,"يا الله إرحمنا. \n" +
                "يا الله إسمعنا. \n" +
                "يا الله أنُظر إلينا. \n" +
                "يا الله إطلِّع علينا. \n" +
                "يا الله تراءَف علينا. \n" +
                "نحنُ شَعبُكَ. \n" +
                "نحنُ جُبلَتُك. \n" +
                "نجّنا مِن أعدائِنا. \n" +
                "نجّنا مِن الغلاء. \n" +
                "نحن عَبيدُك. \n" +
                "أنتَ إبن الله. \n" +
                "آمنا بِكَ. \n" +
                "لأنكَ أتيت وخَلَّصتنا. \n" +
                "تَعهّدنا بخلاصِك. \n" +
                "وأغفر لنا خطايانا. \n");
        values.put(Contract.HymnEntry.CONTENT_COPTIC,"إفنوتي ناي نان. \n" +
                "إفنوتي سوتيَم إيرون. \n" +
                "إفنوتي سومس إيرون. \n" +
                "إفنوتي جوشت إيرون. \n" +
                "إفنوتي شيَنهيت خارون. \n" +
                "آنون خابيك لاؤس. \n" +
                "آنون خابيك إبلازما. \n" +
                "ناهمين إيفول خين نين جاجي. \n" +
                "ناهمين إيفول ها أُو إهفون. \n" +
                "آنون خا نيك إفي آيك. \n" +
                "إيّوس ثي اوس إنثوك. \n" +
                "أنّاهتي إيروك. \n" +
                "جىَ أك إى أك سوتي إمّون. \n" +
                "جيم بين شيني خين بيك أُوجاي. \n" +
                "أووه كانين نوفي نان إيفول. \n");


        long rowInserted11= db.insert(Contract.HymnEntry.TABLE_NAME,null,values);

        values.put(Contract.HymnEntry.COLUMN_ID,"201");
        values.put(Contract.HymnEntry.NAME," انثوتى تى شورى ");
        values.put(Contract.HymnEntry.CONTENT,"انت هي المجمرة الذهب النقي الحاملة جمر النار المبارك.");
        values.put(Contract.HymnEntry.CONTENT_COPTIC,"إنثو تيه تي شوري : إن نوب إنكاثاروس : إتفاي خا بي جيبس : إن إكروم إت اسمارؤوت.");

        long rowInserted12= db.insert(Contract.HymnEntry.TABLE_NAME,null,values);

        values.put(Contract.HymnEntry.COLUMN_ID,"202");
        values.put(Contract.HymnEntry.NAME," شارى افنوتى ");
        values.put(Contract.HymnEntry.CONTENT,"يرفع الله هناك، خطايا الشعب، من قبل المحرقات، ورائحة البخور.  \n" +
                "\n" +
                "مبارك أنت بالحقيقة، مع أبيك الصالح، والروح القدس، لأنك اتيت وخلصتنا.ارحمنا.");
        values.put(Contract.HymnEntry.CONTENT_COPTIC,"شاري إفنوتي أولي إمماﭫ:إنني نوﭭي إنتيه بي لاؤس:إيفول هيتين بي إتشليل:نيم بي إسطوي إنتيه بي إسطوي نوفي. \n" +
                "\n" +
                "إك إسمارؤوت آليثوس:نيم بيك يوت إن آغاثوس:نيم بي إبنيفما إثؤواب : ﭽيه آك آي آك سوتي إممون ناي نان.");

        long rowInserted13= db.insert(Contract.HymnEntry.TABLE_NAME,null,values);

        values.put(Contract.HymnEntry.COLUMN_ID,"203");
        values.put(Contract.HymnEntry.NAME," لبش الهوس الثانى ");
        values.put(Contract.HymnEntry.CONTENT,"فلنشكر المسيح إلهنا، مع المرتل داود النبي  .  \n" +
                "\n" +
                "لأنه خلق السماوات وجنودها، وأسس الأرض على المياه.  \n" +
                "\n" +
                "هذان الكوكبان العظيمان الشمس والقمر، جعلهما ينيران في الفلك.  \n" +
                "\n" +
                "اخرج الرياح من خباياها، نفخ في الأشجار حتى أزهرت.  \n" +
                "\n" +
                "أمطر مطراً على وجه الأرض حتى أنبتت وأعطت ثمرها.  \n" +
                "\n" +
                "اخرج ماء من صخرة وسقى شعبه في البرية.  \n" +
                "\n" +
                "صنع الإنسان كشبهه وصورته لكي يباركه.  \n" +
                "\n" +
                "فلنسبحه و نرفع اسمه ونشكره لان رحمته كائنة إلى الأبد.  \n" +
                "\n" +
                "بصلوات المرتل داود، يا رب انعم لنا بمغفرة خطايانا.  \n" +
                "\n" +
                "بشفاعات والدة الإله القديسة مريم، يا رب انعم لنا بمغفرة خطايانا.  \n" +
                "\n" +
                "بشفاعات كل صفوف الملائكة، يا رب انعم لنا بمغفرة خطايانا.  \n" +
                "\n" +
                "مبارك أنت بالحقيقة مع أبيك الصالح والروح القدس، لأنك أتيت وخلصتنا.");
        values.put(Contract.HymnEntry.CONTENT_COPTIC,"مارين اؤونه ايفول : امبي اخريستوس بينوتي : نيم بي ايروبسالتيس : دافيد بي ابروفيتيس.  \n" +
                "\n" +
                "جى اف ثاميو اني فيؤوي: نيم نوذيناميس : افهيسنتي :امبي كاهي هيجين ني موأو.  \n" +
                "\n" +
                "ناي نيشتي امفوس تير : بيري نيم بيوه : افكاف إف ايرأوأويني : خين بيستير اوما.  \n" +
                "\n" +
                "افئيني ان هان ثيوو : ايفول خين نيف اهور : افنيفي انسا ني اششين : شا انتو فيري ايفول.  \n" +
                "\n" +
                "افهوأوو انؤمون هؤو : هيجين ابهو ام ابكاهي : شانتيف روت ابشوى : انتيفتي امبيف اوطاه.  \n" +
                "\n" +
                "افئيني ان أوموؤو : ايفول خين اوبترا : اف اتسو امبيف لاؤس : ان اهري هي ابشافى.  \n" +
                "\n" +
                "افثاميو امبي رومي : كاطا بيف ايني : نيم تيف هيكون : اثريف اسمو ايروف.  \n" +
                "\n" +
                "مارين هوس ايروف : تين اتشيسي ام بيف ران : تين اؤونه ناف ايفول : جى بيف ناي شوب شا اينيه.  \n" +
                "\n" +
                "هيتين ني افشي : انتى بي ايروبسالتيس دافيد : ابتشويس اري اهموت نان : امبيكو ايفول انتى نين نوفي.  \n" +
                "\n" +
                "هيتين ني ابريسفيا : انتى تي ثيؤطوكوس اثؤواب ماريا : ابتشويس . . .  \n" +
                "\n" +
                "هيتين ني ابريسفيا : انتى ابخوروس تيرف انتى ني انجيلوس : ابتشويس . . .  \n" +
                "\n" +
                "اك اسمارؤوت اليثوس : نيم بيك يوت ان اغاثوس : نيم بي بنيفما : اثؤواب جى اك اى اك سوتى اممون. ");

        long rowInserted14= db.insert(Contract.HymnEntry.TABLE_NAME,null,values);

        String flag;
//        if(rowInserted == -1)
            flag = "doesnt work";
//        else
//            flag = "row inserted work";
//
//        db.close();
        return flag;
   }

//    public boolean found(Hymn m) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        db = this.getWritableDatabase();
//        String columns[] = {Contract.HymnEntry.COLUMN_ID, Contract.HymnEntry.NAME, Contract.HymnEntry.CONTENT, Contract.HymnEntry.CONTENT_COPTIC};
//        String selection = Contract.HymnEntry.COLUMN_ID + " = '" + m.id + "'";
//        Cursor c = db.query(Contract.HymnEntry.TABLE_NAME, columns, selection, null, null, null, null);
//        if (c.getCount() > 0) {
//            c.close();
//            db.close();
//            return true;
//        }
//        else {
//            c.close();
//            db.close();
//
//            return false;
//        }
//    }

    public void deleteHymns() {
        int id =116;
        SQLiteDatabase db = this.getReadableDatabase();
        db = this.getWritableDatabase();
        String where = Contract.HymnEntry.COLUMN_ID+ "=?";
        String whereArgs[] = {"" + id};
        db.delete(Contract.HymnEntry.TABLE_NAME, where, whereArgs);

        db.close();


    }

    public ArrayList<Hymn> getAllHymnView() {
        ArrayList<Hymn> movies = new ArrayList<Hymn>();
        SQLiteDatabase db = this.getReadableDatabase();
        db = this.getWritableDatabase();

        String[] column = {Contract.HymnEntry.COLUMN_ID, Contract.HymnEntry.NAME, Contract.HymnEntry.CONTENT, Contract.HymnEntry.CONTENT_COPTIC};
        Cursor c = db.query(Contract.HymnEntry.TABLE_NAME, column, null, null, null, null, null);

        int id = c.getColumnIndex(Contract.HymnEntry.COLUMN_ID);
        int name = c.getColumnIndex(Contract.HymnEntry.NAME);
        int content = c.getColumnIndex(Contract.HymnEntry.CONTENT);
        int coptic = c.getColumnIndex(Contract.HymnEntry.CONTENT_COPTIC);

        Hymn[] hymn = new Hymn[c.getCount()];

        for(int i = 0 ; i<c.getCount();i++) {
            while (c.moveToNext()) {
                hymn[i] = new Hymn();
                hymn[i].id = c.getInt(id);
                hymn[i].name = c.getString(name);
                hymn[i].content = c.getString(content);
                hymn[i].content_coptic=c.getString(coptic);
                movies.add(hymn[i]);
            }
        }



        c.close();
        db.close();

        return movies;
    }
}

