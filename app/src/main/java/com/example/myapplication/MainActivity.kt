package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProfileScreen()
                }
            }
        }
    }
}

@Composable
fun ProfileScreen() {
    val scrollState = rememberScrollState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Profile Image
        Surface(
            modifier = Modifier
                .size(170.dp),
            shape = CircleShape,
            border = BorderStroke(4.dp, MaterialTheme.colorScheme.primary),
            shadowElevation = 8.dp
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_pic), // Updated to your provided image
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Name and Student ID
        Text(
            text = stringResource(id = R.string.devName),
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = stringResource(id = R.string.student_id),
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Education Info Card
        InfoCard(
            title = "ข้อมูลการศึกษา",
            icon = Icons.Default.School
        ) {
            InfoItem(icon = Icons.Default.HistoryEdu, text = stringResource(id = R.string.student_year))
            InfoItem(icon = Icons.Default.AccountBalance, text = stringResource(id = R.string.faculty))
            InfoItem(icon = Icons.Default.Computer, text = stringResource(id = R.string.major))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Position Card
        InfoCard(
            title = "ตำแหน่งและความรับผิดชอบ",
            icon = Icons.Default.Badge,
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
        ) {
            InfoItem(
                icon = Icons.Default.Groups, 
                text = stringResource(id = R.string.position),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Contact Card
        InfoCard(
            title = "ช่องทางการติดต่อ",
            icon = Icons.Default.ContactSupport
        ) {
            InfoItem(icon = Icons.Default.Phone, text = stringResource(id = R.string.phone))
            InfoItem(icon = Icons.Default.Email, text = stringResource(id = R.string.email))
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun InfoCard(
    title: String, 
    icon: ImageVector, 
    containerColor: Color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), thickness = 0.8.dp, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.1f))
            content()
        }
    }
}

@Composable
fun InfoItem(icon: ImageVector, text: String, fontWeight: FontWeight = FontWeight.Normal) {
    Row(
        modifier = Modifier.padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon, 
            contentDescription = null, 
            modifier = Modifier.size(20.dp),
            tint = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text, 
            fontSize = 15.sp, 
            lineHeight = 22.sp,
            fontWeight = fontWeight,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    MyApplicationTheme {
        ProfileScreen()
    }
}
