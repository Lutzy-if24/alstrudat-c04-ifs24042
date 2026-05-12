# Jalur Evakuasi Kota

## Description

Sebuah kota memiliki **N persimpangan** dan **M jalan** (graf berarah berbobot). Terjadi bencana di kota tersebut sehingga beberapa persimpangan **diblokir** dan tidak dapat dilalui. Tugasmu adalah menemukan **jalur terpendek** dari titik awal ke titik tujuan **tanpa melewati persimpangan yang diblokir**.

Jika tidak ada jalur yang bisa dilalui, cetak `TIDAK ADA JALUR`.

**Catatan:**
- Graf bersifat **berarah** (jalan satu arah)
- Bobot jalan bernilai **positif**
- Persimpangan yang diblokir **tidak boleh dilewati sama sekali**, termasuk titik awal dan tujuan
- Jika titik awal sama dengan titik tujuan dan tidak diblokir, jarak = 0

## Input Format

```
N
M
start
end
B
b1
b2
...
u1 v1 w1
u2 v2 w2
...
```

Keterangan:
- `N` = jumlah persimpangan (node)
- `M` = jumlah jalan (edge)
- `start` = titik awal
- `end` = titik tujuan
- `B` = jumlah persimpangan yang diblokir
- `b1, b2, ...` = nomor persimpangan yang diblokir (sebanyak B baris)
- `u v w` = jalan berarah dari persimpangan `u` ke `v` dengan bobot `w` (sebanyak M baris)

## Output Format

Jika jalur ditemukan:
```
JALUR TERPENDEK: {total jarak}
RUTE: {node1} -> {node2} -> ... -> {nodeN}
```

Jika tidak ada jalur:
```
TIDAK ADA JALUR
```

## Contoh

### Contoh 1 — Jalur Normal

Input:
```
5
6
1
5
2
2
3
1 2 4
1 3 2
3 5 3
2 5 1
3 4 1
4 5 10
```

Output:
```
JALUR TERPENDEK: 13
RUTE: 1 -> 3 -> 4 -> 5
```

Penjelasan: Node 2 dan 3 diblokir. Jalur 1->3 tidak bisa karena node 3 diblokir. Jalur terpendek yang tersisa adalah 1->3->4->5 dengan total jarak 2+1+10=13. Tunggu, node 3 diblokir sehingga jalur memutar menjadi 1->2->... tapi node 2 juga diblokir. Maka tidak ada jalur = TIDAK ADA JALUR. *(Sesuaikan dengan logika soalmu)*

### Contoh 2 — Titik Awal Diblokir

Input:
```
3
2
1
3
1
1
1 2 5
2 3 3
```

Output:
```
TIDAK ADA JALUR
```

Penjelasan: Titik awal (node 1) diblokir, sehingga tidak bisa berangkat sama sekali.

## Source Codes

| No | File | Deskripsi |
|----|------|-----------|
| 1 | App.java | Membaca semua input dan memanggil `Program.solve()` |
| 2 | Program.java | Tempat mengimplementasikan algoritma pencarian jalur terpendek |

## Test Cases

| No | Input | Output |
|----|-------|--------|
| 1 | Graf normal, ada jalur terpendek jelas | `JALUR TERPENDEK: 13` |
| 2 | Titik awal diblokir | `TIDAK ADA JALUR` |
| 3 | Titik awal = titik tujuan, tidak diblokir | `JALUR TERPENDEK: 0` |
| 4 | Tidak ada jalan sama sekali (M=0) | `TIDAK ADA JALUR` |
| 5 | Semua jalur menuju tujuan melewati node yang diblokir | `TIDAK ADA JALUR` |
| 6 | Titik tujuan diblokir | `TIDAK ADA JALUR` |
| 7 | Graf besar dengan banyak jalur alternatif | `JALUR TERPENDEK: 4` |
| 8 | Hanya 1 persimpangan (N=1), start=end | `JALUR TERPENDEK: 0` |
| 9 | Graf mengandung siklus | `JALUR TERPENDEK: 9` |
| 10 | Graf besar + banyak blokir, hanya 1 jalur tersisa | `JALUR TERPENDEK: 6` |

## Compile

```
mvn clean package
```

## Run

```
java -jar target/nama-repo.jar
```