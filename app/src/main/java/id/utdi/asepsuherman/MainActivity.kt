package id.utdi.asepsuherman

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.dp
import id.utdi.asepsuherman.ui.theme.EcomfTheme


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcomfTheme {
                // Scaffold adalah komponen Material Design yang menyediakan kerangka kerja
                // untuk mengatur elemen-elemen UI lainnya seperti App Bar dan Content.
                Scaffold(
                    // TopBar adalah App Bar di bagian atas layar.
                    topBar = {
                        TopAppBar(title = { Text(text = "Product List") })
                    },
                    content = {
                        // Menampilkan komponen ProductListDemo di dalam Content.
                        ProductListDemo()
                    }
                )
            }
        }
    }
}

// Data class untuk merepresentasikan produk.
data class Product(val name: String, val description: String, val imageRes: Int)

@Composable
fun ProductListDemo() {
    // Daftar merek produk.
    val brands = listOf("Adidas", "Nike", "Puma", "Nasa", "Stoneisland", "Cpcompany", "Lacoste")
    // selectedBrand digunakan untuk melacak merek yang dipilih oleh pengguna.
    var selectedBrand by remember { mutableStateOf(brands.first()) }

    // Column adalah tata letak linier vertikal untuk mengatur elemen-elemen UI.
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(60.dp) )
        // LazyRow adalah tata letak horizontal yang berisi elemen-elemen yang bisa digulir.
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(brands) { brand ->
                BrandItem(
                    brand = brand,
                    isSelected = brand == selectedBrand,
                    onBrandSelected = { selectedBrand = brand }
                )
            }
        }

        // selectedBrand digunakan untuk menampilkan produk yang sesuai dengan merek yang dipilih.
        val products = when (selectedBrand) {
            "Adidas" -> adidasProducts
            "Nike" -> nikeProducts
            "Puma" -> pumaProducts
            "Nasa" -> nasaProducts
            "Stoneisland" -> stoneislandProduct
            "Cpcompany" -> cpcompanyProduct
            "Lacoste" -> lacosteProduct
            else -> emptyList()
        }

        // LazyColumn adalah tata letak vertikal yang berisi elemen-elemen yang bisa digulir.
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(products) { product ->
                ProductCard(product)
            }
        }
    }
}

@Composable
fun BrandItem(
    brand: String,
    isSelected: Boolean,
    onBrandSelected: (String) -> Unit
) {
    // Mencocokkan merek dengan gambar logo yang sesuai.
    val logoRes = when (brand) {
        "Adidas" -> R.drawable.adidas_removebg_preview
        "Nike" -> R.drawable.nike_logotype_removebg_preview
        "Puma" -> R.drawable.logo_de_puma__logo_de_puma__logo_de_puma_s__blanco__mam_fero__texto_png_removebg_preview
        "Nasa" -> R.drawable.nasa_logo_famous_logos_decals__decal_sticker__150_removebg_preview
        "Stoneisland" -> R.drawable.stone_island_logo_png_vector__cdr__free_download_removebg_preview
        "Cpcompany" -> R.drawable.c0858e51_d2e8_4ca0_bad3_8c99c957869b_removebg_preview
        "Lacoste" -> R.drawable.lacoste_logo_png_transparent___svg_vector___freebie_supply_removebg_preview
        else -> R.drawable.adidas_removebg_preview
    }

    // Card adalah komponen Material Design yang menampilkan konten dalam sebuah kotak.
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onBrandSelected(brand) }

    ) {
        Box(
            modifier = Modifier
                .size(90.dp)
        ) {
            Image(
                painter = painterResource(id = logoRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}


@Composable
fun ProductCard(product: Product) {
    // Card adalah komponen Material Design yang menampilkan konten dalam sebuah kotak.
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // Column digunakan untuk mengatur elemen-elemen UI secara vertikal.
        Column(modifier = Modifier.padding(16.dp)) {
            // Image adalah elemen untuk menampilkan gambar.
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            // Text adalah elemen untuk menampilkan teks.
            Text(text = product.name, style = MaterialTheme.typography.headlineSmall)
            // Text adalah elemen untuk memberi jarak.
            Spacer(modifier = Modifier.height(16.dp) )
            Text(text = product.description, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

// Daftar produk untuk merek Adidas.
val adidasProducts = listOf(
    Product("Adidas Campus", "Adidas reissue a fan-favourite and total archival icon, the Samba OG. Buttery soft leather adorns the uppers, offset by grey suede overlays, and stamped with gold SAMBA detailing. The tongue features a bluebird branded patch, and the shoes are finished with those world famous 3-stripes. Leather Uppers Suede Overlays Gold Foil Details Bluebird Branded Tongue Patch Gum Rubber Midsole Check out Adidas Samba OG feature here.", R.drawable.adidas_campus_00s_shoes___aluminium___core_black___aluminium_removebg_preview),
    Product("Adidas Sneakers", "Explore Forum shoes and sneakers at adidas. Put your feet at the point where sport meets style in shoes with a retro hoops vibe.", R.drawable.forum_shoes___sneakers___adidas_us_removebg_preview),
    Product("Adidas Samba", "escription Adidas Campus 00s Shoes Adidas Campus 00s Shoes in Aluminium and Core Black. Drawing creative influence from the iconic Campus 80, the adidas Campus 00s is designed with neutral suede uppers with contrasting leather 3-stripe sidewall detailing. Featuring a perforated midfoot, a vintage style padded tongue and a textile lined inner, this adidas Campus 00s also features an icy white rubber outsole with a grippy herringbone tread. Finished with thick flat laces, a 3D style tongue badge.", R.drawable.adidas_samba_og___cloud_white_core_black_clear_granite___uk10_removebg_preview),

)

// Daftar produk untuk merek Nike.
val nikeProducts = listOf(
    Product("Nike Dunk Low Black White Panda", "Find many great new & used options and get the best deals for Nike Dunk Low Black White Panda (DD1391-100) Men's Size 8-14 at the best online prices at eBay! Free shipping for many products!", R.drawable.nike_dunk_low_black_white_panda__dd1391_100__men_s_size_8_11_5_removebg_preview),
    Product("The Air Jordan 1 High Shadow 2.0", "The Air Jordan 2021 leaks went crazy just a few months back but lately, we’ve seen the news start to cool down but now we’re back with yet another new offering of the Air Jordan 1 High in a colorway that many will be out to get. Dressed in Black. White and Light Smoke Grey, this AJ1 features a colorway anyone can get behind arriving in a classic black and white colorway with grey detailing that adds a crisp touch to the iconic sneaker. While no leaked images have surfaced just yet, we expect White leather to dress the side paneling and toes while black overlays cover the majority of the sneaker. Matching laces and tongue provide even more darkness looming over the sneaker while Light Grey covers the ankles. A white rummer midsole and grey outsole will then complete the newly coveted design from Jordan Brand.", R.drawable.the_air_jordan_1_high__shadow_2_0__release_details_news_and_release_dates_removebg_preview),
    Product("Jordan 1 Mid", "This iteration of the AJ1 reimagines Mike's first signature model with a fresh mix of colors. Premium materials, soft cushioning and a padded ankle collar offer total support and celebrate the shoe that started it all.", R.drawable.jordan_1_mid__gs__size_5_5y_wmns_7_white_multi_color_black_554725_190_fast_ship_removebg_preview),
)

val pumaProducts = listOf(
    Product("PUMA × RIPNDIP", "・PUMA × RIPNDIPのコラボレーション・長年愛されるスウェードスニーカー・RIPNDIPのアイコンであるロードナーマル サイズ 26cm 26.5cm 27cm 27.5cm 28cm 28.5cm 29cm US US 8 US 8.5 US 9 US 9.5 US 10 US 10.5 US 11", R.drawable.ripndip_suede___blk___29_removebg_preview),
    Product("PUMA Suede Classic XXI Men's Sneakers, Black/White, 13", "The Suede Hit The Scene In 1968 And Has Been Changing The Game Ever Since. It’S Been Worn By The Icons Of Every Generation And It’S Stayed Classic Through It All. This Year, We Relaunch The Suede With Fresh Colorways And Subtle Design Updates. Classic As Ever, For All-Time. Features & Benefits Better Materials: The Leather Sourced In This Product Comes From Environmentally Responsible Leather Manufacturing, And Is Audited And Certified Via The Leather Working Group Protocol Details Classic S", R.drawable.puma_suede_classic_xxi_men_s_sneakers__black_white__13_removebg_preview),
    Product("PUMA Roma Basic Little Kids' Shoes", "From the PUMA Archive in 1968, this lightweight training shoe was equipped with comfort-enhancing features such as a thick padded tongue and orthopaedic arch supports. This material update sees the use of leather in homage to the original shoe", R.drawable.roma_basic_little_kids__shoes___puma_removebg_preview),
)

val nasaProducts = listOf(
    Product("NASA Casual Coat", "The NASA Casual Coat is a stylish and comfortable jacket for everyday casual wear. With a touch of cool design, this jacket displays the iconic NASA logo, adding an awesome futuristic vibe. Made from high-quality materials, this jacket suits various everyday activities such as strolling, shopping, or meeting friends. It's the perfect choice for those who want to look fashionable while expressing their interest in space exploration.", R.drawable.men_jacket_new_fashion_casual_men_coat_solid_pilot_bomber_jacket___gray___usa_m_removebg_preview),
    Product("NASA Flight Jacket", "The NASA Flight Jacket draws inspiration from classic pilot jackets worn by astronauts. Precision-designed, this jacket offers the perfect blend of style and functionality. Equipped with insulating layers that make it suitable for cold weather, as well as numerous practical pockets for storing essentials. This jacket features a bold NASA logo on the sleeve, exuding a sense of allegiance to this leading space agency. It's the ideal choice for those seeking a cool look and readiness for any adventure.", R.drawable.plus_size_casual_flight_jacket_men___navy_blue___3xl_removebg_preview),
    Product("NASA Japanesee", "The NASA Japanese Embroidered Jacket is the perfect fusion of elegant Japanese style and the spirit of space exploration. This jacket features a design inspired by beautiful Japanese embroidery traditions, with intricate details showcasing the NASA logo, spacecraft imagery, and stars. Crafted from high-quality materials, this jacket offers both comfort and a remarkable appearance. It's the ideal choice for those who appreciate the art of embroidery and have an interest in outer space exploration.", R.drawable.japanese_embroidered_jacket_removebg_preview),
)

val stoneislandProduct = listOf(
    Product("Stone Island Junior Logo-Patch Cotton Hoodie - Neutrals", "logo-patch cotton hoodie from Stone Island Junior featuring logo patch at the sleeve, front pouch pocket, classic hood, long sleeves, ribbed cuffs, ribbed hem, beige and cotton.", R.drawable.stone_island_junior_logo_patch_cotton_hoodie___neutrals_removebg_preview),
    Product("Stone Island Teen Black Cotton Sweatshirt - 14 YEARS", "Discover the new autumn-winter 2022 collection. Luxury fashion for men and children. Wide selection of luxury. Quick checkout. Wide choice of articles. Customer support 24/24. Worldwide shipping. Secure payments", R.drawable.stone_island_teen_black_cotton_sweatshirt___14_years_removebg_preview),
    Product("Stone Island Junior TEEN logo-patch hooded zipper - Neutrals", "TEEN logo-patch hooded zipper from Stone Island Junior featuring cotton, logo patch at the sleeve, front zip fastening, classic hood, long sleeves, straight hem and neutral.", R.drawable.stone_island_junior_teen_logo_patch_hooded_zipper___neutrals_removebg_preview),
)

val cpcompanyProduct = listOf(
    Product("The Football Casual", "The Football Casual is often over looked as a trend setter but this cultures influence stretches further than you think. It has helped the brands such as Lacoste and CP Company.", R.drawable.the_football_casual_removebg_preview),
    Product("C.P. Company: The Original Italian Sportswear Brand - Black", "C.P. Company is the Original Italian Sportswear brand specializing in garment dyed technology since 1971. Shop on the Official Online Store.", R.drawable.cp___black_removebg_preview),
    Product("C.P. Company: The Original Italian Sportswear Brand - Blue", "C.P. Company is the Original Italian Sportswear brand specializing in garment dyed technology since 1971. Shop on the Official Online Store.", R.drawable.cp___blue_removebg_preview),
)

val lacosteProduct = listOf(
    Product("Lacoste Men's Classic Fit L.12.12 Polo Shirts Black L1212-031", "Lacoste Men's Classic Fit L.12.12 Polo Shirts Manufacturer #: L1212-031 Color: Black MSRP: $98.00 A signature design from the Lacoste wardrobe, this L.12.12 Polo Shirt in cotton petit piqué combines comfort and elegance. A chic, timeless essential, ideal for any occasion. Ribbed collar and armbands 2-button placket Mother-of-pearl buttons Classic fit Cotton petit piqué Cotton (100%) 100% Authenticity Guaranteed", R.drawable.lacoste_navi_removebg_preview),
    Product("Polo Lacoste Sport Preto", "Polo Masculina Lacoste Sport. Gola em ribana com três botões Piqué tecnológico ultra-dry resistente e respirável Debrum na gola e nos punhos Logo na parte interna da gola Aplique de crocodilo verde bordado no tórax Poliéster (100%)", R.drawable.polo_lacoste_sport_preto_removebg_preview),
    Product("Lacoste-Contrast Trim Colour Block Polo Shirt Navy Flour Pine", "Stretch colour block polo shirt. Revisiting an elegant classic, this stretch cotton piqué polo shirt boasts the iconic colourblock design. Contrasting buttons, a slim cut and thoughtful finishes complete the piece. Stretch cotton piqué Two-button ribbed polo collar Slim fit Ribbed finishes at sleeve ends Embroidered green crocodile on chest Main fabric: Cotton (94%), Elastane (6%) / Rib edge: Cotton (100%)", R.drawable.lacoste_contrast_trim_colour_block_polo_shirt_navy_flour_pine_removebg_preview),
)

@Preview(showBackground = true)
@Composable
fun ProductListDemoPreview() {
    EcomfTheme {
        ProductListDemo()
    }
}