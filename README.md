# Quarkus Todo Service

Bu proje, Quarkus ile geliştirilmiş bir Todo servisidir.

## Docker ile Çalıştırma

Tüm uygulamayı (PostgreSQL, pgAdmin ve Quarkus) tek komutla başlatmak için:

```bash
docker-compose up -d
```

Bu komut:
- PostgreSQL 16 veritabanını başlatır (port: 5432)
- pgAdmin 4'ü başlatır (port: 5050)
- Quarkus uygulamasını başlatır (port: 8080)

## API Endpointlerini Test Etmek için cURL Komutları

### 1. Tüm Todo'ları Listele
```bash
curl -X GET http://localhost:8080/api/todos | jq
```

### 2. ID'ye Göre Todo Getir
```bash
curl -X GET http://localhost:8080/api/todos/1 | jq
```

### 3. Yeni Todo Oluştur
```bash
curl -X POST \
  -H "Content-Type: application/json" \
  -d '{"title":"Test Todo","description":"Test Description"}' \
  http://localhost:8080/api/todos | jq
```

### 4. Todo Güncelle
```bash
curl -X PUT \
  -H "Content-Type: application/json" \
  -d '{"title":"Updated Todo","description":"Updated Description"}' \
  http://localhost:8080/api/todos/1 | jq
```

### 5. Todo'yu Tamamlandı Olarak İşaretle
```bash
curl -X PATCH \
  -H "Content-Type: application/json" \
  -d '{"completed":true}' \
  http://localhost:8080/api/todos/1/complete | jq
```

### 6. Todo Sil
```bash
curl -X DELETE http://localhost:8080/api/todos/1 | jq
```

## Swagger UI

API dokümantasyonuna ve test arayüzüne erişmek için:

- [http://localhost:8080/q/swagger-ui](http://localhost:8080/q/swagger-ui)

## GitHub Repository

Proje kaynak kodu: [https://github.com/PehlivanMert/quarkus-todo-service](https://github.com/PehlivanMert/quarkus-todo-service)

## 🚀 Özellikler

- RESTful API (CRUD operasyonları)
- PostgreSQL veritabanı entegrasyonu
- OpenAPI (Swagger) dokümantasyonu
- Sağlık kontrolleri
- Docker ve Kubernetes desteği
- Kapsamlı test kapsamı
- Modern Java 17 özellikleri

## 🛠 Teknoloji Yığını

- Java 17
- Quarkus 3.7.1
- PostgreSQL 16
- Docker & Docker Compose
- Maven
- JUnit 5
- REST Assured
- OpenAPI/Swagger

## 📋 Gereksinimler

- JDK 17
- Maven 3.8+
- Docker ve Docker Compose
- PostgreSQL 16 (Docker ile sağlanıyor)

## 🚀 Başlangıç

### 1. Projeyi Klonlama

```bash
git clone https://github.com/pehlivanmert/quarkus-todo-service.git
cd quarkus-todo-service
```

### 2. Veritabanı Kurulumu

Docker Compose ile PostgreSQL ve pgAdmin'i başlatın:

```bash
docker-compose up -d
```

Bu komut:
- PostgreSQL 16 veritabanını başlatır (port: 5432)
- pgAdmin 4'ü başlatır (port: 5050)
- Veritabanı verilerini kalıcı hale getirir

### 3. Veritabanı Bağlantı Bilgileri

- **Host**: localhost
- **Port**: 5432
- **Database**: tododb
- **Username**: postgres
- **Password**: postgres

### 4. pgAdmin Erişimi

- **URL**: http://localhost:5050
- **Email**: admin@admin.com
- **Password**: admin

### 5. Uygulamayı Çalıştırma

Geliştirme modunda çalıştırmak için:

```bash
./mvnw quarkus:dev
```

Uygulama http://localhost:8080 adresinde çalışmaya başlayacaktır.

## 📚 API Dokümantasyonu

Swagger UI üzerinden API dokümantasyonuna erişebilirsiniz:
- http://localhost:8080/q/swagger-ui

## 🧪 Testler

Testleri çalıştırmak için:

```bash
./mvnw test
```

## 🐳 Docker ile Çalıştırma

Uygulamayı Docker ile çalıştırmak için:

```bash
# JVM modunda build
./mvnw package -Dquarkus.container-image.build=true

# Native modunda build (isteğe bağlı)
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

## 📦 Kubernetes Dağıtımı

Kubernetes manifestlerini oluşturmak için:

```bash
./mvnw package -Dquarkus.kubernetes.deploy=true
```

## 🔍 Sağlık Kontrolleri

Sağlık kontrol endpoint'leri:
- http://localhost:8080/q/health
- http://localhost:8080/q/health/live
- http://localhost:8080/q/health/ready

## 📝 API Endpoint'leri

### Todo İşlemleri

- `POST /todos` - Yeni todo oluştur
- `GET /todos` - Tüm todoları listele
- `GET /todos/{id}` - ID'ye göre todo getir
- `PUT /todos/{id}` - Todo güncelle
- `DELETE /todos/{id}` - Todo sil

## 🔐 Güvenlik

- Geliştirme ortamında temel güvenlik önlemleri
- Üretim ortamı için JWT tabanlı kimlik doğrulama eklenebilir

## 📈 Performans

- Quarkus'un hızlı başlatma süresi
- Düşük bellek kullanımı
- Native image desteği
- Reactive programlama desteği

## 🤝 Katkıda Bulunma

1. Fork'layın
2. Feature branch oluşturun (`git checkout -b feature/amazing-feature`)
3. Değişikliklerinizi commit edin (`git commit -m 'feat: Add amazing feature'`)
4. Branch'inizi push edin (`git push origin feature/amazing-feature`)
5. Pull Request oluşturun

## 📄 Lisans

Bu proje MIT lisansı altında lisanslanmıştır. Detaylar için [LICENSE](LICENSE) dosyasına bakın.

## 👥 İletişim

Proje Sahibi - [@PehlivanMert](https://github.com/PehlivanMert)

Proje Linki: [https://github.com/PehlivanMert/quarkus-todo-service.git](https://github.com/PehlivanMert/quarkus-todo-service.git) 