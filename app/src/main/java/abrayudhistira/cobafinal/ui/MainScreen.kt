package abrayudhistira.cobafinal.ui

import abrayudhistira.cobafinal.R
import abrayudhistira.cobafinal.model.JenisProperti
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeApp(
    modifier: Modifier = Modifier,
    onHalamanProperti: () -> Unit,
    onHalamanJenisProperti: () -> Unit,
    onHalamanPemilik: () -> Unit,
    onHalamanManajerProperti: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Halo, Selamat Datang di Management Property",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp, top = 15.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Text(
            text = "Silahkan Pilih Menu yang ingin anda kelola",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp, top = 15.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                ManageBox(
                    title = "Properti",
                    description = "Kelola Properti",
                    backgroundColor = Color.DarkGray, // Darker box background
                    iconResource = R.drawable.property,
                    onClick = { onHalamanProperti() }
                )
                Spacer(Modifier.height(16.dp))
                ManageBox(
                    title = "Jenis Properti",
                    description = "Kelola Jenis Properti",
                    backgroundColor = Color.DarkGray,
                    iconResource = R.drawable.jenisproperty,
                    onClick = { onHalamanJenisProperti() }
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                ManageBox(
                    title = "Pemilik",
                    description = "Kelola Pemilik",
                    backgroundColor = Color.DarkGray, // Darker box background
                    iconResource = R.drawable.ownerwhite,
                    onClick = { onHalamanPemilik() }
                )
                Spacer(Modifier.height(16.dp))
                ManageBox(
                    title = "Manajer Properti",
                    description = "Kelola Manajer Properti",
                    backgroundColor = Color.DarkGray,
                    iconResource = R.drawable.manager,
                    onClick = { onHalamanManajerProperti() }
                )
            }
        }
    }
}

@Composable
fun ManageBox(
    title: String,
    description: String,
    backgroundColor: Color,
    iconResource: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor, shape = RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp) // Menambahkan jarak antar elemen vertikal
        ) {
            // Ikon di atas
            Image(
                painter = painterResource(id = iconResource),
                contentDescription = "$title Icon",
                modifier = Modifier
                    .size(30.dp) // Sesuaikan ukuran ikon jika diperlukan
                    //.clip(CircleShape)
            )

            // Judul di bawah ikon
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = description,
                fontSize = 11.sp,
                color = Color(0xFFE0E0E0) // Warna abu-abu untuk deskripsi
            )
        }
    }
}