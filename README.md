# Quarkus Todo Service

Bu proje, Quarkus framework'ü kullanılarak geliştirilmiş bir Todo uygulamasıdır. PostgreSQL veritabanı kullanır ve Docker ile containerize edilmiştir.

## Teknolojiler

- Quarkus 3.8.1
- Java 17
- PostgreSQL
- Docker & Docker Compose
- PgAdmin 4

## Quarkus vs Spring Boot

### Quarkus'un Avantajları

1. **Hızlı Başlangıç Süresi**
   - Quarkus, native-image desteği ile saniyeler içinde başlar
   - Spring Boot'a göre çok daha hızlı başlangıç süresi
   - Düşük bellek kullanımı

2. **Container-First Yaklaşım**
   - Docker ve Kubernetes için optimize edilmiş
   - Daha küçük container imajları
   - Daha az kaynak tüketimi

3. **Hot Reload**
   - Geliştirme sırasında anında değişiklik görüntüleme
   - Spring Boot'a göre daha hızlı hot reload
   - Daha az yeniden başlatma ihtiyacı

4. **PanacheRepository**
   - JPA'nın üzerine inşa edilmiş, daha basit ve güçlü bir repository pattern
   - Aktif kayıt pattern'i ile daha az boilerplate kod
   - Otomatik CRUD operasyonları
   - Örnek kullanım:
     ```java
     @ApplicationScoped
     public class TodoRepository implements PanacheRepository<Todo> {
         // Temel CRUD operasyonları otomatik olarak gelir
         // Özel sorgular için:
         public List<Todo> findUncompleted() {
             return find("completed", false).list();
         }
     }
     ```

### Spring Boot'un Avantajları

1. **Olgunluk ve Ekosistem**
   - Daha geniş topluluk desteği
   - Daha fazla hazır çözüm ve kütüphane
   - Daha fazla dokümantasyon ve kaynak

2. **Esneklik**
   - Daha fazla konfigürasyon seçeneği
   - Daha fazla entegrasyon imkanı
   - Daha fazla özelleştirme

3. **Öğrenme Eğrisi**
   - Daha fazla kaynak ve örnek
   - Daha fazla geliştirici deneyimi
   - Daha kolay iş bulma imkanı

### Ne Zaman Quarkus Kullanmalı?

1. **Mikroservis Mimarisi**
   - Container tabanlı deployment
   - Düşük kaynak kullanımı gerektiren senaryolar
   - Hızlı başlangıç süresi önemli olduğunda

2. **Serverless Uygulamalar**
   - AWS Lambda, Azure Functions gibi serverless platformlar
   - Soğuk başlangıç süresi önemli olduğunda
   - Düşük bellek kullanımı gerektiğinde

3. **Yüksek Performans Gerektiren Uygulamalar**
   - Düşük latency gerektiren senaryolar
   - Yüksek throughput gerektiren uygulamalar
   - Native-image kullanımı uygun olduğunda

### Ne Zaman Spring Boot Kullanmalı?

1. **Monolitik Uygulamalar**
   - Geleneksel enterprise uygulamalar
   - Karmaşık iş mantığı gerektiren projeler
   - Geniş ekosistem ihtiyacı olduğunda

2. **Hızlı Geliştirme**
   - Kısa sürede prototip geliştirme
   - Geniş topluluk desteği gerektiğinde
   - Çok sayıda hazır çözüm ihtiyacı olduğunda

3. **Kurumsal Entegrasyonlar**
   - Eski sistemlerle entegrasyon
   - Karmaşık enterprise gereksinimleri
   - Geniş konfigürasyon ihtiyacı

## Özellikler

- RESTful API endpoints
- PostgreSQL veritabanı entegrasyonu
- Docker container desteği
- Swagger/OpenAPI dokümantasyonu
- Validation ve error handling
- Loglama

## Gereksinimler

- Docker
- Docker Compose
- Java 17 (geliştirme için)

## Hızlı Başlangıç

### 1. Projeyi Klonlayın

```bash
git clone https://github.com/PehlivanMert/quarkus-todo-service.git
cd quarkus-todo-service
```

### 2. Uygulamayı Başlatın

```bash
# Tüm container'ları başlatın (ilk kez build eder)
docker compose up --build -d

# Logları kontrol edin
docker compose logs -f todo-app
```

### 3. Uygulamayı Durdurun

```bash
# Tüm container'ları durdurun ve volume'ları temizleyin
docker compose down -v
```

## API Endpoints

Uygulama başladıktan sonra aşağıdaki endpoint'ler kullanılabilir:

- `GET /api/todos` - Tüm todo'ları listeler
- `GET /api/todos/{id}` - Belirli bir todo'yu getirir
- `POST /api/todos` - Yeni todo oluşturur
- `PUT /api/todos/{id}` - Todo'yu günceller
- `PATCH /api/todos/{id}` - Todo'nun tamamlanma durumunu günceller
- `DELETE /api/todos/{id}` - Todo'yu siler

## Veritabanı Yönetimi

PgAdmin 4 arayüzüne http://localhost:5050 adresinden erişebilirsiniz:

- Email: admin@admin.com
- Şifre: admin

Veritabanı bağlantı bilgileri:
- Host: todo-postgres
- Port: 5432
- Database: tododb
- Username: postgres
- Password: postgres

## Swagger/OpenAPI Dokümantasyonu

API dokümantasyonuna http://localhost:8080/q/swagger-ui adresinden erişebilirsiniz.

## Geliştirme

### Yerel Geliştirme Ortamı

```bash
# Maven ile build
./mvnw clean package

# Quarkus dev modunda çalıştırma
./mvnw quarkus:dev
```

### Docker ile Geliştirme

```bash
# Container'ları yeniden build et ve başlat
docker compose up --build -d

# Logları izle
docker compose logs -f todo-app

# Container'ları durdur
docker compose down
```

## Proje Yapısı

```
src/main/java/com/example/todo/
├── dto/           # Data Transfer Objects
├── model/         # JPA Entities
├── repository/    # Database Repositories
├── resource/      # REST Endpoints
└── service/       # Business Logic
```

## Lisans

Bu proje MIT lisansı altında lisanslanmıştır. 