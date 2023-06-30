package com.example.todo_android.Screen

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberImagePainter
import com.example.todo_android.Navigation.Action.RouteAction
import com.example.todo_android.Navigation.NAV_ROUTE
import com.example.todo_android.R
import com.example.todo_android.Util.MyApplication

fun goChangePassword(route: NAV_ROUTE, routeAction: RouteAction) {
    routeAction.navTo(route)
}

//fun changeNicknameAndProfile(
//    token: String,
//    nickname: String,
//    body: MultipartBody.Part,
//    routeAction: RouteAction,
//) {
//
//    var changeNicknameAndProfileResponse: ChangeNicknameAndProfileResponse? = null
//
//
//    var retrofit = Retrofit.Builder().baseUrl("https://plotustodo-ctzhc.run.goorm.io/")
//        .addConverterFactory(GsonConverterFactory.create()).build()
//
//    var changeNicknameAndProfileRequest: ChangeNicknameAndProfileRequest =
//        retrofit.create(ChangeNicknameAndProfileRequest::class.java)
//
//    changeNicknameAndProfileRequest.requestChangeNicknameAndProfile(token, nickname, body)
//        .enqueue(object : Callback<ChangeNicknameAndProfileResponse> {
//
//            // 성공 했을때
//            override fun onResponse(
//                call: Call<ChangeNicknameAndProfileResponse>,
//                response: Response<ChangeNicknameAndProfileResponse>,
//            ) {
//                changeNicknameAndProfileResponse = response.body()
//
//                when (changeNicknameAndProfileResponse?.resultCode) {
//                    "200" -> {
//
//                        MyApplication.prefs.setData("nickname", nickname)
//                        MyApplication.prefs.setData("image",
//                            changeNicknameAndProfileResponse!!.data.image)
//                        routeAction.goBack()
//
//                        Log.d("changeNickname&Profile",
//                            "resultCode : " + changeNicknameAndProfileResponse?.resultCode)
//                        Log.d("changeNickname&Profile",
//                            "resultCode : " + changeNicknameAndProfileResponse?.data)
//                    }
//                    "500" -> {
//                        Log.d("changeNickname&Profile",
//                            "resultCode : " + changeNicknameAndProfileResponse?.resultCode)
//                    }
//                }
//            }
//
//            // 실패 했을때
//            override fun onFailure(call: Call<ChangeNicknameAndProfileResponse>, t: Throwable) {
//                Log.e("changeNickname&Profile", t.message.toString())
//            }
//        })
//}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun ProfileScreen(routeAction: RouteAction) {

    val token = "Token ${MyApplication.prefs.getData("token", "")}"

    var email: String = MyApplication.prefs.getData("email", "")
    var nickname by remember { mutableStateOf(MyApplication.prefs.getData("nickname", "")) }
    var openDialog by remember { mutableStateOf(false) }
    var imdel by remember { mutableStateOf(true) }
    var imageUri = rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    if (openDialog) {
        setImageDialog(
            onDismissRequest = { openDialog = false},
            imageUri = imageUri
        )
    }

    val imageResource = rememberImagePainter(
        data = if(
//            MyApplication.prefs.getData("image", "")  == null ||
            imageUri.value.isEmpty()
        ) {
            R.drawable.defaultprofile
    } else {
        imageUri.value
    })


//    val responseDefaultProfileImage = MyApplication.prefs.getData("defaultProfileImage", "")
//    val defaultProfileImageDecodedBytes = Base64.decode(responseDefaultProfileImage, Base64.DEFAULT)
//    val defaultProfileImageBitmap = BitmapFactory.decodeByteArray(
//        defaultProfileImageDecodedBytes, 0, defaultProfileImageDecodedBytes.size
//    )
//
//    val decodeDefaultImageFile: File? = File.createTempFile("temp", null, context.cacheDir)
//    val defaultOutPutStream = FileOutputStream(decodeDefaultImageFile)
//    defaultProfileImageBitmap?.compress(Bitmap.CompressFormat.PNG, 100, defaultOutPutStream)
//    defaultOutPutStream.close()
//
//    val responseImage = MyApplication.prefs.getData("image", "")
//    val decodedBytes = Base64.decode(responseImage, Base64.DEFAULT)
//    val decodedImage = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
//
//    val decodeFile: File? = File.createTempFile("temp", null, context.cacheDir)
//    val outputStream = FileOutputStream(decodeFile)
//    decodedImage?.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
//    outputStream.close()
//
//    val imageUri = rememberSaveable {
//        mutableStateOf(decodeFile?.toUri())
//    }

//    var painter = rememberImagePainter(data = imageUri.value, builder = {
//        if (imageUri.value != null) {
//            placeholder(R.drawable.defaultprofile)
//        }
//    })
//
//
//    val file = imageUri.value?.let { uri ->
//        val contentResolver = context.contentResolver
//        val inputStream = contentResolver.openInputStream(uri)
//        val tempFile = File.createTempFile("image", null, context.cacheDir)
//        tempFile.outputStream().use { outputStream ->
//            inputStream?.copyTo(outputStream)
//        }
//        tempFile
//    }
//
//    val requestFile = file?.asRequestBody("image/jpeg".toMediaTypeOrNull())
//    val body = requestFile?.let {
//        MultipartBody.Part.createFormData("image", file.name, requestFile)
//    }

    Scaffold(modifier = Modifier
        .fillMaxWidth()
        .imePadding(), topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "프로필 수정",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp
            )
        }, navigationIcon = {
            IconButton(onClick = {
                routeAction.goBack()
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
            }
        }, actions = {
            Text(text = "완료", modifier = Modifier
                .padding(30.dp)
                .clickable {
//                    val currenNickname = MyApplication.prefs.getData("nickname", nickname)
//                    if ((body != null) || !(nickname.equals(currenNickname))) {
//                        changeNicknameAndProfile(token, nickname, body!!, routeAction)
//                    }
                })
        })
    }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.padding(vertical = 36.dp))
            
            Box(modifier = Modifier.padding(8.dp)) {
                Image(
                    painter = imageResource,
                    contentDescription = "profileImage",
                    modifier = Modifier
                        .size(90.dp)
                        .clickable {
                            openDialog = true
                        }
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Row(modifier = Modifier.padding(2.dp)) {
//                    Text(text = "", modifier = Modifier.width(28.dp))
                    Image(
                        painter = painterResource(R.drawable.profile_bottom_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(start = 60.dp, top = 60.dp)
                            .clip(CircleShape)
                    )
                }
            }

//            Image(
//                painter = painterResource(id = defaultImage),
//                contentDescription = "profileImage",
//                modifier = Modifier
//                    .size(90.dp)
//                    .clickable {
//                        openDialog = true
//                    },
//                contentScale = ContentScale.Crop
//            )

            Spacer(modifier = Modifier.padding(vertical = 26.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Text(
                    text = "닉네임",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    lineHeight = 21.sp,
                )
            }

            Spacer(modifier = Modifier.padding(vertical = 7.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                value = nickname,
                onValueChange = {
                    nickname = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xffffffff),
                    disabledLabelColor = Color(0xffffffff),
                    focusedIndicatorColor = Color(0xffD0D0D0),
                    unfocusedIndicatorColor = Color(0xffD0D0D0)
                )
            )

            Spacer(modifier = Modifier.padding(vertical = 14.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Text(
                    text = "이메일", fontWeight = FontWeight.Bold, fontSize = 14.sp, lineHeight = 21.sp
                )
            }

            Spacer(modifier = Modifier.padding(vertical = 7.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .focusable(false),
                value = email,
                onValueChange = {
                    email = it
                },
                singleLine = true,
                readOnly = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xffF1F1F1),
                    disabledLabelColor = Color(0xffF1F1F1),
                    focusedIndicatorColor = Color(0xffD0D0D0),
                    unfocusedIndicatorColor = Color(0xffD0D0D0)
                )
            )

            Spacer(modifier = Modifier.padding(vertical = 25.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(
                        width = 0.5.dp,
                        color = Color(0xff424242),
                        shape = RoundedCornerShape(percent = 8)
                    ), colors = ButtonDefaults.buttonColors(Color.White), onClick = {
                    goChangePassword(NAV_ROUTE.CHANGEPASSWORD, routeAction)
                }, shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "비밀번호 변경",
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 22.sp
                )
            }

            Spacer(modifier = Modifier.padding(vertical = 115.dp))

            Button(
                modifier = Modifier
                    .width(130.dp)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(Color(0xffE9E9E9)),
                onClick = {
                    routeAction.navTo(NAV_ROUTE.DELETEACCOUNT)
                },
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "계정 탈퇴하기",
                    color = Color(0xff8D8D8D),
                    fontSize = 12.sp,
                    lineHeight = 22.sp
                )
            }
        }
    }
}

@Composable
fun setImageDialog(
    onDismissRequest: () -> Unit,
    imageUri: MutableState<String>,
) {
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                imageUri.value = it.toString()
                Log.v("setImage", "image: ${uri}")
            }
        }


    Dialog(onDismissRequest = { onDismissRequest }) {
        Surface(shape = RoundedCornerShape(15.dp), color = Color.White) {
            Column(
                modifier = Modifier.width(285.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Spacer(modifier = Modifier.padding(vertical = 15.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .padding(start = 55.dp, end = 55.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xffFFDAB9)),
                    onClick = {
                        launcher.launch("image/*")
                        onDismissRequest()
                    },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "앨범에서 선택",
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                Spacer(modifier = Modifier.padding(vertical = 10.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .padding(start = 55.dp, end = 55.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xffE9E9E9)),
                    onClick = {
//                        defaultImage.value = R.drawable.defaultprofile
                        onDismissRequest()
                    },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "기본 이미지로 변경",
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                Spacer(modifier = Modifier.padding(vertical = 15.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    androidx.compose.material.TextButton(modifier = Modifier
                        .background(
                            Color(
                                0xffE9E9E9
                            )
                        )
                        .weight(1f), onClick = {
                        onDismissRequest()
                    }) {
                        Text(text = "닫기", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

//@Composable
//fun setImageDialog() {
//
//    val imageUri = rememberSaveable { mutableStateOf("") }
//    val painter = rememberImagePainter(
//        if (imageUri.value.isEmpty()) {
//            R.drawable.defaultprofile
//        } else {
//            imageUri.value
//        }
//    )
//
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent(),
//    ) { uri: Uri? ->
//        uri?.let {
//            imageUri.value = it.toString()
//        }
//    }
//
//    var openDialog by remember { mutableStateOf(true) }
//
//    if (openDialog) {
//        Dialog(
//            onDismissRequest = {
//                openDialog = false
//            }) {
//            Surface(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(400.dp),
//                shape = RoundedCornerShape(12.dp),
//                color = Color.White
//            ) {
//                Column(
//                    modifier = Modifier.fillMaxSize(),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Button(
//                        onClick = {
//                            launcher.launch("image/*")
//                            openDialog = false
//                        },
//                        shape = RoundedCornerShape(10.dp),
//                        modifier = Modifier
//                            .width(250.dp)
//                            .height(45.dp)
//                            .background(Color.White))
//                    {
//                        Text(text = "앨범에서 선택")
//                    }
//
//                    Spacer(modifier = Modifier.height(15.dp))
//
//                    Button(
//                        onClick = {
//                            openDialog = false
//                        },
//                        shape = RoundedCornerShape(10.dp),
//                        modifier = Modifier
//                            .width(250.dp)
//                            .height(45.dp)
//                            .background(Color.White))
//                    {
//                        Text(text = "기본 이미지로 변경")
//                    }
//                }
//            }
//        }
//    }
//}