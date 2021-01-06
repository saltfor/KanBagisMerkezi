
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {

    private static int kullaniciSayisi;   //öncelik sıralamasında kuyruga indis olabilmesi için
    private static List<Kullanici> kullanicilar=new ArrayList<>(); //kullanıcı nesneleri listesi
    private static List<Kullanici> kanBagisCizgesi=new ArrayList<>(); //kan bagıs çizgesi
    private static int[][] kan_iliski_matrisi= new int[8][8];  //RH çizgesi
    private static String[] kangrubuDizisi=new String[]{"0 RH-","0 RH+","A RH-","A RH+","B RH-","B RH+","AB RH-","AB RH+"};

    public static void menuGoster(){
        //menü ve seçim işlemleri
        System.out.println("-------------------------------------------------");
        System.out.println("KAN BAGIS MERKEZINE HOSGELDINIZ");
        System.out.println("1)Yeni Kayıt Ekle");
        System.out.println("2)Kan Bağışı İlişkisi Tanımla");
        System.out.println("3)Kan Grupları Arası Kan Bağışı İlişki Çizgesini Yazdır");
        System.out.println("4)Merkezdeki Kan Bağışlarının Çizgesini Yazdır");
        System.out.print("Seçmek istediğiniz işlemin numarasını girip Enter tuşuna basınız:");
        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();
        if(i==1){
            //kullanıcı ekleme işlemleri
            Kullanici k=new Kullanici();
            System.out.print("TC Kimlik Numarası girip Enter tuşuna basınız:");
            scan = new Scanner(System.in);
            String tc = scan.next();
            k.TC=tc;
            System.out.print("Ad Soyad girip Enter tuşuna basınız:");
            scan = new Scanner(System.in);
            String adSoyad = scan.next();
            k.adSoyad=adSoyad;
            System.out.print("Bağış Yapacak Kullanıcı ise E değilse(Kan Alacaksa) H yazıp Enter tuşuna basınız:");
            scan = new Scanner(System.in);
            String bagisMi;
            while(true){
                bagisMi = scan.next();
                if(bagisMi.equals("E")){
                    k.bagisciMi=true;
                    k.aliciMi=false;
                    break;
                }
                else if(bagisMi.equals("H")){
                    k.bagisciMi=false;
                    k.aliciMi=true;
                    break;
                }
                else
                    System.out.print("Hatalı giriş yaptınız. Tekrar giriniz.");
            }
            System.out.print("Kan Grubunun harfini yazıp Enter tuşuna basınız:");
            scan = new Scanner(System.in);
            String kanGrubu = scan.next();
            k.kanGrubu=kanGrubu;
            System.out.print("Kan Grubunun RH değerini (+ veya -) girip Enter tuşuna basınız:");
            scan = new Scanner(System.in);
            String RH = scan.next();
            k.RH=RH.charAt(0);
            System.out.print("HIV hastalığı varsa E yoksa H yazıp Enter tuşuna basınız:");
            scan = new Scanner(System.in);
            String HIV;
            while(true){
                HIV = scan.next();
                if(HIV.equals("E")){k.HIV=true; break;}
                else if(HIV.equals("H")){k.HIV=false; break;}
                else System.out.println("Hatalı giriş yaptınız. Tekrar giriniz");
            }
            System.out.print("Hepatit B hastalığı varsa E yoksa H yazıp Enter tuşuna basınız:");
            scan = new Scanner(System.in);
            String hepatitB;
            while(true){
                hepatitB = scan.next();
                if(hepatitB.equals("E")){k.hepatitB=true; break;}
                else if(hepatitB.equals("H")){k.hepatitB=false; break;}
                else  System.out.println("Hatalı giriş yaptınız. Tekrar giriniz");
            }
            if(HIV.equals("E") || hepatitB.equals("E")) { //HIV veya hepatit varsa bagıscı olamaz
                if (bagisMi.equals("E")) {
                    System.out.println("Hastalığınız olduğu için Kan Bağışçısı olamazsınız. Sadece Kan Alıcısı olabilirsiniz. " +
                            "Sisteme kaydınız Kan Alıcısı olarak oluşturulacak.");
                    k.bagisciMi = false;
                    k.aliciMi = true;
                }
            }
            System.out.println("Sisteme kaydınız oluşturulmuştur.");
            //öncelik sırası atama
            k.kanAlicioncelikSiralamasi=kullaniciSayisi;
            kullaniciSayisi++;
            kullanicilar.add(k);
        }
        else if(i==2){
            //kan bagıs işlemi gerçekleştirme
            System.out.println("Sistemde "+kullaniciSayisi+" kişi kayıtlıdır.");
            System.out.println("Kan bağışı yapacak kişinin ad soyadını giriniz:");
            scan = new Scanner(System.in);
            String kisi1=scan.next();
            System.out.println("Yapılacak bağış için kan alıcısının ad soyadını giriniz:");
            scan = new Scanner(System.in);
            String kisi2=scan.next();
            String kan1="",kan2="";
            boolean islemDevam=true;
            Kullanici k1=new Kullanici(),k2=new Kullanici();
            for(int y=0;y<kullaniciSayisi;y++){
                if(kullanicilar.get(y).adSoyad.equals(kisi1)) {
                    if (kullanicilar.get(y).bagisciMi) { //ilk girilen kullanıcı bagıscı mı
                        kan1 = kullanicilar.get(y).kanGrubu + " RH" + kullanicilar.get(y).RH;
                        k1=kullanicilar.get(y);
                    }
                    else {
                        System.out.println("Girilen ilk kullanıcı bağışcı olmadığı için bağış işlemi gerçekleştirilemez.");
                        islemDevam=false;
                    }
                }
                if(kullanicilar.get(y).adSoyad.equals(kisi2))
                    if(kullanicilar.get(y).aliciMi) { //ikinci girilen kişi alıcı mı
                        kan2 = kullanicilar.get(y).kanGrubu + " RH" + kullanicilar.get(y).RH;
                        k2=kullanicilar.get(y);
                    }
                    else {
                        System.out.println("Girilen ikinci kullanıcı kan alıcısı olmadığı için bağış işlemi gerçekleştirilemez.");
                        islemDevam = false;
                    }
            }
            if(islemDevam){ //kan bagıscı ve kan alıcı kişilerinde uyum varsa işlem gerçekleşir
                int index1=0,index2=0;
                for(int z=0;z<8;z++){ //kan gruplarının indislerini belirleme
                    if(kangrubuDizisi[z].equals(kan1))
                        index1=z;
                    if(kangrubuDizisi[z].equals(kan2))
                        index2=z;
                }

                if(kan_iliski_matrisi[index1][index2]==1) {     //kanbagıs çizgesinden bagıs işlemi için kontrol
                    System.out.println(kisi1 + " kişisi " + kisi2 + " kişisine kan verebilir. Kan verme işlemi gerçekleştirildi");
                    //kan bagıs çizgesine kayıt ekleme
                    kanBagisCizgesi.add(k1);
                    kanBagisCizgesi.add(k2);
                }
                else
                    System.out.println(kisi1+" kişisi "+kisi2+" kişisine kan veremez. Kan bagısı işlemi başarısız");

            }

        }
        else if(i==3){
            //kan grupları arası kan bagısı ilişkisi çizgesinin yazdırılması
            for (int k = 0; k < 8; k++)
                for (int x = 0; x < 8; x++)
                    if (kan_iliski_matrisi[k][x] == 1) //k indisindeki kan grubu x indisindeki kan grubuna kan verebiliyorsa
                        System.out.println( kangrubuDizisi[k]+" kan grubuna sahip bağışçı "+kangrubuDizisi[x]+" kan grubuna sahip alıcıya kan verebilir");
                    else
                        System.out.println( kangrubuDizisi[k]+" kan grubuna sahip bağışçı "+kangrubuDizisi[x]+" kan grubuna sahip alıcıya kan veremez");
        }
        else if(i==4){
            //kan bagısı işlemi gerçekleştirilen işlemlerin çizgesinin yazdırılması
            for (int k = 0; k < kanBagisCizgesi.size()-1; k=k+2) //ilk kullanıcı veren ikinci kullanıcı alan oldugu için 2 şer artım yapıldı
                    System.out.println(kullanicilar.get(k).adSoyad+" kullanıcısı "+kullanicilar.get(k+1).adSoyad+" kullanıcısına kan vermiştir");
        }
        else{
            System.out.println("Hatalı giriş yaptınız. Menüye geri dönülüyor.");

        }
    }

    public static void kanGrubuIliskiTanımla(){
        //kan grubu indisleri sırasıyla 0-,0+,A-,A+,B-,B+,AB-,AB+ şeklinde tanımlanmıştı.

        kan_iliski_matrisi[0][0]=1; // 0- 0- ye kan verebilir
        kan_iliski_matrisi[0][1]=1; // 0- 0+ ya kan verebilir.
        kan_iliski_matrisi[0][2]=1;kan_iliski_matrisi[0][3]=1;kan_iliski_matrisi[0][4]=1;
        kan_iliski_matrisi[0][5]=1;kan_iliski_matrisi[0][6]=1;kan_iliski_matrisi[0][7]=1;

        kan_iliski_matrisi[1][0]=0;kan_iliski_matrisi[1][1]=1;kan_iliski_matrisi[1][2]=0;kan_iliski_matrisi[1][3]=1;    //0+ kimlere verebilir
        kan_iliski_matrisi[1][4]=0;kan_iliski_matrisi[1][5]=1;kan_iliski_matrisi[1][6]=0;kan_iliski_matrisi[1][7]=1;

        kan_iliski_matrisi[2][0]=0;kan_iliski_matrisi[2][1]=0;kan_iliski_matrisi[2][2]=1;kan_iliski_matrisi[2][3]=1;    //A- kimlere verebilir
        kan_iliski_matrisi[2][4]=0;kan_iliski_matrisi[2][5]=0;kan_iliski_matrisi[2][6]=1;kan_iliski_matrisi[2][7]=1;

        kan_iliski_matrisi[3][0]=0;kan_iliski_matrisi[3][1]=0;kan_iliski_matrisi[3][2]=0;kan_iliski_matrisi[3][3]=1;    //A+ kimlere verebilir
        kan_iliski_matrisi[3][4]=0;kan_iliski_matrisi[3][5]=0;kan_iliski_matrisi[3][6]=0;kan_iliski_matrisi[3][7]=1;

        kan_iliski_matrisi[4][0]=0;kan_iliski_matrisi[4][1]=0;kan_iliski_matrisi[4][2]=0;kan_iliski_matrisi[4][3]=0;    //B- kimlere verebilir
        kan_iliski_matrisi[4][4]=1;kan_iliski_matrisi[4][5]=1;kan_iliski_matrisi[4][6]=1;kan_iliski_matrisi[4][7]=1;

        kan_iliski_matrisi[5][0]=0;kan_iliski_matrisi[5][1]=0;kan_iliski_matrisi[5][2]=0;kan_iliski_matrisi[5][3]=0;    //B+ kimlere verebilir
        kan_iliski_matrisi[5][4]=0;kan_iliski_matrisi[5][5]=1;kan_iliski_matrisi[5][6]=0;kan_iliski_matrisi[5][7]=1;

        kan_iliski_matrisi[6][0]=0;kan_iliski_matrisi[6][1]=0;kan_iliski_matrisi[6][2]=0;kan_iliski_matrisi[6][3]=0;    //AB- kimlere verebilir
        kan_iliski_matrisi[6][4]=0;kan_iliski_matrisi[6][5]=0;kan_iliski_matrisi[6][6]=1;kan_iliski_matrisi[6][7]=1;

        kan_iliski_matrisi[7][0]=0;kan_iliski_matrisi[7][1]=0;kan_iliski_matrisi[7][2]=0;kan_iliski_matrisi[7][3]=0;    //AB+ kimlere verebilir
        kan_iliski_matrisi[7][4]=0;kan_iliski_matrisi[7][5]=0;kan_iliski_matrisi[7][6]=0;kan_iliski_matrisi[7][7]=1;
    }

    public static void main(String[] args) {
        kullaniciSayisi=0;
        kanGrubuIliskiTanımla();
        while (true){
            menuGoster(); //her işlemden sonra menü tekrar gösterilecektir.
        }
    }
}
class Kullanici{
    public String TC;
    public String adSoyad;
    public boolean bagisciMi;
    public boolean aliciMi;
    public String kanGrubu;
    public char RH;
    public boolean HIV;
    public boolean hepatitB;
    public int kanAlicioncelikSiralamasi;
}
