# KanBagisMerkezi
Kan Bağış Merkezi için oluşturulmuş sistem


Bir kan bağışı merkezine gelen bağışçılar ve kana ihtiyacı olanları eşleştirip kaydını çizge (graph) üzerinde gösteren bir yazılım. Kan merkezine gelen insanların TC kimlik numarası, bağışçı, kan alıcı, kan grubu, RH bilgileri ile HIV, Hepatit B hastalık durumu bilgileri vardır. Bağışçılar ve kan verdiklerinin ilişkisi çizgeyle gösterilir ve çizge komşuluk listesi ile tanımlanır. Ayrıca kan grubu ve RH'a göre kan bağışlayabilme için ayrıca bir çizge kullanılmış ve bağışçının kan alıcıya kan verebilirliği bu çizgeden kontrol yapılarak belirlenmiştir.

İnsan sınıfında TC kimlik numarası, bağışçı mı (boolean), kan alıcı mı (boolean), kan grubu, RH (+ veya -), HIV (boolean) ve Hepatit B (boolean) hastalığı ve kan alıcı ise öncelik sırası değişkenleri tanımlanmıştır.

Bir kullanıcı kan bağışçısı veya alıcısı olabilir. Eğer bir insanda HIV veya Hepatit B hastalıklarından en az biri varsa bu insan bağışçı olamaz, sadece kan alabilir.
Çizgeler yönlü olarak tanımlanmştır.

4 işlemli bir menü mevcuttur. İlk işlem kullanıcı ekleme işlemidir. İkinci işlem girdi olarak verilen iki kullanıcının kan bağışı yapılabiliyorsa kan bağışı ilişkisi tanımlanıp çizgeye kaydedilecektir. Üçüncü işlemde kan grubu ve RH’a göre kan bağışı ilişkisini gösteren çizge yazdırılmaktadır. Dördüncü işlemde kan bağışı merkezine kaydedilen herkesin kan bağışı ilişkisini gösteren çizge yazdırılmaktadır.

