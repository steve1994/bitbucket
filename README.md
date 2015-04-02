# bitbucket
TES ANDROID SUITMEDIA BANDUNG

SPESIFIKASI SOAL 

Buat Aplikasi di iPhone/Android (sesuai pilihan) dengan spesifikasi :
terdiri 4 screen :
1. Home; berisi masukan nama
2. pilih event/guest; berisi label/textview nama sesuai masukan, 2 button :
    a. pilih event, jika dipilih menuju ke screen 3.
    b. pilih guest, jika dipilih menuju ke screen 4.
3. list/table view events (data dummy minimal 4, tapi harus mengimplementasikan model event),
   dengan atribut image, nama, dan tanggal. Jika dipilih kembali ke screen 2, button a diisi nama event yang
   dipilih (tidak boleh buat screen baru).
4. grid/collectionview guests (data dari http://dry-sierra-6832.herokuapp.com/api/people), dengan atribut
   nama, birthdate. Image dummy. Jika dipilih kembali ke screen 2, button b diisi nama guest yang dipilih (tidak
   boleh buat screen baru). Dan muncul toast/alert dialog/alert view dengan ketentuan :
      Jika tanggal lahir kelipatan 2 keluarnya blackberry
      Jika tanggal lahir kelipatan 3 keluarnya android
      Jika tanggal lahir kelipatan (2 dan 3) keluarnya iOS
      Selain itu keluarnya feature phone
