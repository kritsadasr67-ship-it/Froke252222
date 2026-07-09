package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    InClassWorkshopUI()
                }
            }
        }
    }
}

@Composable
fun InClassWorkshopUI() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // ส่วนหัวรายงานตามสั่ง
        Text(
            text = "In-Class Assignment: Jetpack Compose",
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        // Part 1: ผลการ Run Text, Image, Icon
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "ผลการรัน: Text, Image และ Icon", fontWeight = FontWeight.Bold)
                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
                
                // 1. Text
                Text(
                    text = "สวัสดีชาว SNRU (Component: Text)",
                    fontSize = 18.sp,
                    color = Color(0xFF2E7D32),
                    fontWeight = FontWeight.Medium
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 2. Image (apple)
                    Image(
                        painter = painterResource(id = R.drawable.apple), 
                        contentDescription = "Image Component",
                        modifier = Modifier.size(70.dp).border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                    )
                    Spacer(modifier = Modifier.width(40.dp))
                    // 3. Icon
                    Icon(
                        imageVector = Icons.Default.Star, 
                        contentDescription = "Icon Component",
                        modifier = Modifier.size(70.dp),
                        tint = Color(0xFFFFD600)
                    )
                }
            }
        }

        // Part 2: แผนผัง Diagram ทั้ง 3 (ucD, sqD, ClassD)
        Text(text = "Part 2: Workshop Diagrams", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        // แสดงรูปภาพ Diagram (เปลี่ยนจากการใช้ Image เป็น Composable ที่มีข้อความ)
        DiagramSection(title = "Use Case Diagram (ucD)") {
            UseCaseDiagramContent()
        }
        
        DiagramSection(title = "Sequence Diagram (sqD)") {
            SequenceDiagramContent()
        }
        
        DiagramSection(title = "Class Diagram (ClassD)") {
            ClassDiagramContent()
        }

        // ข้อมูลผู้พัฒนา (ดึงจาก strings.xml)
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            color = Color.Red.copy(alpha = 0.1f)
        ) {
            Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(id = R.string.devName),
                    color = Color.Red,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "นักศึกษาผู้ส่งงาน", fontSize = 12.sp)
            }
        }
        
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun DiagramSection(title: String, content: @Composable () -> Unit) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { 
                Toast.makeText(context, "Viewing $title", Toast.LENGTH_SHORT).show() 
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.AccountTree, contentDescription = null, modifier = Modifier.size(20.dp), tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = title, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            }
            Spacer(modifier = Modifier.height(12.dp))
            // ส่วนแสดงเนื้อหา Diagram
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                content()
            }
        }
    }
}

@Composable
fun UseCaseDiagramContent() {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // Actor
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(50.dp), tint = Color(0xFF1976D2))
            Text("Customer", fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
        
        // System Boundary
        Box(
            modifier = Modifier
                .width(200.dp)
                .fillMaxHeight(0.95f)
                .border(2.dp, Color.DarkGray)
                .padding(8.dp)
        ) {
            Text(
                "SNRU Online Shop", 
                modifier = Modifier.align(Alignment.TopCenter).padding(top = 2.dp), 
                fontSize = 10.sp, 
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                UseCaseItem("เลือกซื้อสินค้า")
                Spacer(modifier = Modifier.height(12.dp))
                UseCaseItem("หยิบใส่ตะกร้า")
                Spacer(modifier = Modifier.height(12.dp))
                UseCaseItem("ชำระเงิน")
            }
        }
    }
}

@Composable
fun UseCaseItem(name: String) {
    Surface(
        shape = RoundedCornerShape(50),
        border = BorderStroke(1.dp, Color.Black),
        color = Color.White
    ) {
        Text(
            text = name, 
            fontSize = 11.sp, 
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 4.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SequenceDiagramContent() {
    Row(
        modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Lifeline("User", Color(0xFF1976D2))
        
        Column(
            modifier = Modifier.weight(1f).padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MessageLine("1. Login Request", true)
            Spacer(modifier = Modifier.height(30.dp))
            MessageLine("2. Verify User", true)
            Spacer(modifier = Modifier.height(30.dp))
            MessageLine("3. Success (Return)", false)
        }
        
        Lifeline("System", Color(0xFF388E3C))
    }
}

@Composable
fun Lifeline(name: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            color = color,
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.width(70.dp)
        ) {
            Text(
                text = name, 
                color = Color.White, 
                fontSize = 11.sp, 
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(4.dp),
                textAlign = TextAlign.Center
            )
        }
        Box(modifier = Modifier.width(2.dp).fillMaxHeight().border(1.dp, Color.LightGray, shape = RoundedCornerShape(0.dp)))
    }
}

@Composable
fun MessageLine(text: String, isForward: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = text, fontSize = 9.sp, fontWeight = FontWeight.Medium)
        Text(
            text = if (isForward) "───────────>" else "<───────────",
            fontSize = 12.sp,
            color = Color.DarkGray
        )
    }
}

@Composable
fun ClassDiagramContent() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ClassBox(
            "Member", 
            listOf("- id: String", "- name: String"), 
            listOf("+ register()", "+ login()")
        )
        
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(horizontal = 8.dp)) {
            Text("1", fontSize = 10.sp)
            Text("──────", fontSize = 10.sp)
            Text("*", fontSize = 10.sp)
        }

        ClassBox(
            "Order", 
            listOf("- orderId: Int", "- date: Date"), 
            listOf("+ checkout()")
        )
    }
}

@Composable
fun ClassBox(name: String, attributes: List<String>, methods: List<String>) {
    Column(
        modifier = Modifier
            .width(110.dp)
            .border(1.dp, Color.Black)
    ) {
        Text(
            text = name,
            modifier = Modifier.fillMaxWidth().padding(4.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
        HorizontalDivider(color = Color.Black, thickness = 1.dp)
        Column(modifier = Modifier.padding(4.dp)) {
            attributes.forEach { Text(it, fontSize = 9.sp) }
        }
        HorizontalDivider(color = Color.Black, thickness = 1.dp)
        Column(modifier = Modifier.padding(4.dp)) {
            methods.forEach { Text(it, fontSize = 9.sp) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme { InClassWorkshopUI() }
}
