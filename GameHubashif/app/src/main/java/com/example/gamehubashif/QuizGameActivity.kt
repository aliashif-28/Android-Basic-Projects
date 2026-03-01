package com.example.gamehubashif

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizGameActivity : AppCompatActivity() {

    private lateinit var questionText: TextView
    private lateinit var scoreText: TextView
    private lateinit var timerText: TextView
    private lateinit var options: Array<Button>

    private var index = 0
    private var right = 0
    private var wrong = 0

    private var timer: CountDownTimer? = null
    private val timePerQuestion = 15000L

    // ⭐ Question list (tum yaha 200 questions paste kar sakte ho)
    private val allQuestions = listOf(
        Question("2024 me Bharat Ratna kisko diya gaya?", arrayOf("Lal Krishna Advani","Sachin Tendulkar","Amit Shah","Ratan Tata"),0),
        Question("India ka G20 Summit 2023 kis city me hua?", arrayOf("Mumbai","Delhi","Chennai","Kolkata"),1),
        Question("ISRO ka Chandrayaan-3 landing kaha hui?", arrayOf("Moon South Pole","Moon North","Mars","Venus"),0),
        Question("Aditya L1 mission kis field se related?", arrayOf("Sun Study","Moon","Mars","Earth"),0),
        Question("Digital India program kab launch hua?", arrayOf("2015","2016","2017","2018"),0),

        Question("UPI kis organization ne develop kiya?", arrayOf("NPCI","RBI","SBI","SEBI"),0),
        Question("2024 ICC T20 World Cup kisne jeeta?", arrayOf("India","Australia","England","Pakistan"),0),
        Question("India ka current Prime Minister kaun?", arrayOf("Narendra Modi","Rahul Gandhi","Amit Shah","Yogi Adityanath"),0),
        Question("India ka current President kaun?", arrayOf("Droupadi Murmu","Ram Nath Kovind","Pratibha Patil","None"),0),
        Question("India ka current Chief Justice?", arrayOf("DY Chandrachud","NV Ramana","UU Lalit","None"),0),

        Question("Startup India scheme kab launch hui?", arrayOf("2016","2015","2014","2017"),0),
        Question("Make in India ka logo kya?", arrayOf("Lion","Tiger","Elephant","Horse"),0),
        Question("India ka largest state area wise?", arrayOf("Rajasthan","MP","UP","Maharashtra"),0),
        Question("India ka smallest state?", arrayOf("Goa","Sikkim","Tripura","Manipur"),0),
        Question("India ka national animal?", arrayOf("Tiger","Lion","Elephant","Leopard"),0),

        Question("India ka national bird?", arrayOf("Peacock","Sparrow","Crow","Parrot"),0),
        Question("India ka national flower?", arrayOf("Lotus","Rose","Sunflower","Lily"),0),
        Question("India ka national fruit?", arrayOf("Mango","Apple","Banana","Orange"),0),
        Question("India ka national game?", arrayOf("Hockey","Cricket","Kabaddi","Football"),0),
        Question("India ka currency?", arrayOf("Rupee","Dollar","Yen","Euro"),0),

        Question("India ka largest river?", arrayOf("Ganga","Yamuna","Godavari","Krishna"),0),
        Question("India ka highest peak?", arrayOf("K2","Everest","Kanchenjunga","Nanda Devi"),2),
        Question("India ka space agency?", arrayOf("ISRO","NASA","ESA","CNSA"),0),
        Question("ISRO HQ kaha?", arrayOf("Bangalore","Delhi","Mumbai","Chennai"),0),
        Question("Green Hydrogen Mission kis country ka initiative?", arrayOf("India","USA","UK","Japan"),0),

        Question("India ka defence exercise USA ke saath?", arrayOf("Yudh Abhyas","Garuda","Malabar","Indra"),0),
        Question("Malabar exercise kin countries ke beech?", arrayOf("India-USA-Japan-Australia","India-China","India-Russia","India-UK"),0),
        Question("India ka first bullet train project kaha?", arrayOf("Mumbai-Ahmedabad","Delhi-Kolkata","Chennai-Bangalore","None"),0),
        Question("Atmanirbhar Bharat ka goal?", arrayOf("Self reliance","Import","Export","None"),0),
        Question("India ka largest airport?", arrayOf("IGI Delhi","Mumbai","Bangalore","Hyderabad"),0),

        Question("India ka largest port?", arrayOf("Mumbai","Kandla","Chennai","Visakhapatnam"),1),
        Question("India ka biggest dam?", arrayOf("Bhakra","Tehri","Sardar Sarovar","Hirakud"),2),
        Question("India ka biggest solar park?", arrayOf("Bhadla","Pavagada","Rewa","None"),0),
        Question("India ka cleanest city (Swachh Survekshan)?", arrayOf("Indore","Surat","Bhopal","Delhi"),0),
        Question("India ka literacy highest state?", arrayOf("Kerala","Goa","Delhi","TN"),0),

        Question("India ka biggest IT hub?", arrayOf("Bangalore","Hyderabad","Pune","Noida"),0),
        Question("India ka biggest metro network?", arrayOf("Delhi","Mumbai","Chennai","Kolkata"),0),
        Question("India ka national highway network ka rank?", arrayOf("2nd","1st","3rd","4th"),1),
        Question("India ka GDP rank globally?", arrayOf("5th","3rd","7th","10th"),0),
        Question("India ka defence budget rank?", arrayOf("4th","1st","2nd","3rd"),0),

        Question("India ka largest democracy?", arrayOf("India","USA","UK","Canada"),0),
        Question("India ka largest film industry?", arrayOf("Bollywood","Hollywood","Tollywood","None"),0),
        Question("India ka national anthem writer?", arrayOf("Tagore","Gandhi","Nehru","None"),0),
        Question("India ka national song?", arrayOf("Vande Mataram","Jana Gana Mana","Saare Jahan","None"),0),
        Question("India ka republic day?", arrayOf("26 Jan","15 Aug","2 Oct","None"),0),

        Question("India ka independence day?", arrayOf("15 Aug","26 Jan","2 Oct","None"),0),
        Question("India ka Gandhi Jayanti?", arrayOf("2 Oct","14 Nov","15 Aug","None"),0),
        Question("India ka largest festival?", arrayOf("Diwali","Holi","Eid","None"),0),
        Question("India ka yoga day?", arrayOf("21 June","5 June","10 July","None"),0),
        Question("India ka constitution kab lagu?", arrayOf("1950","1947","1948","1952"),0),

        Question("NITI Aayog ka chairman kaun hota hai?", arrayOf("PM","President","FM","CM"),0), Question("NITI Aayog kab bana?", arrayOf("2015","2014","2016","2017"),0),
        Question("Ayushman Bharat scheme kis sector se related?", arrayOf("Health","Education","Defence","Sports"),0),
        Question("PM Jan Dhan Yojana kis liye?", arrayOf("Bank account","Loan","Insurance","Housing"),0),
        Question("PM Awas Yojana kis liye?", arrayOf("Housing","Road","Health","Education"),0),

        Question("Beti Bachao Beti Padhao ka goal?", arrayOf("Girl education","Employment","Health","None"),0),
        Question("Swachh Bharat Mission kab launch hua?", arrayOf("2014","2015","2016","2017"),0),
        Question("Skill India ka aim?", arrayOf("Employment skills","Health","Loan","None"),0),
        Question("Startup India kis sector ko boost karta?", arrayOf("Entrepreneurship","Health","Education","None"),0),
        Question("Make in India ka aim?", arrayOf("Manufacturing","Import","Export","None"),0),

        Question("Pradhan Mantri Kisan Samman Nidhi kis liye?", arrayOf("Farmer income","Loan","Insurance","None"),0),
        Question("PM Ujjwala Yojana kis se related?", arrayOf("LPG","Electricity","Water","Road"),0),
        Question("Jal Jeevan Mission ka aim?", arrayOf("Tap water","Road","Health","None"),0),
        Question("Digital India ka focus?", arrayOf("Digital services","Road","Health","None"),0),
        Question("National Education Policy kab launch?", arrayOf("2020","2019","2021","2018"),0),

        Question("India ka first female President?", arrayOf("Pratibha Patil","Indira Gandhi","Droupadi Murmu","None"),0),
        Question("India ka first PM?", arrayOf("Nehru","Gandhi","Patel","None"),0),
        Question("India ka first woman PM?", arrayOf("Indira Gandhi","Pratibha Patil","None","None"),0),
        Question("India ka first CDS?", arrayOf("Bipin Rawat","Naravane","None","None"),0),
        Question("India ka first woman fighter pilot?", arrayOf("Avani Chaturvedi","Gunjan Saxena","None","None"),0),

        Question("INS Vikrant kya hai?", arrayOf("Aircraft carrier","Submarine","Fighter","None"),0),
        Question("INS Arihant kis type ka vessel?", arrayOf("Nuclear submarine","Carrier","Destroyer","None"),0),
        Question("Tejas kis type ka aircraft?", arrayOf("Fighter jet","Helicopter","Cargo","None"),0),
        Question("Agni missile kis type?", arrayOf("Ballistic","Cruise","Tactical","None"),0),
        Question("BrahMos missile kis type?", arrayOf("Cruise","Ballistic","Nuclear","None"),0),

        Question("India ka largest PSU bank?", arrayOf("SBI","PNB","BOB","None"),0),
        Question("RBI governor ka role?", arrayOf("Monetary policy","Budget","Loan","None"),0),
        Question("SEBI kis se related?", arrayOf("Stock market","Bank","Insurance","None"),0),
        Question("IRDAI kis sector ko regulate karta?", arrayOf("Insurance","Bank","Stock","None"),0),
        Question("NABARD kis se related?", arrayOf("Agriculture","Stock","Loan","None"),0),

        Question("Indian Railways ka HQ?", arrayOf("Delhi","Mumbai","Chennai","None"),0),
        Question("Vande Bharat kya hai?", arrayOf("Train","Bus","Plane","None"),0),
        Question("India ka longest railway platform?", arrayOf("Hubballi","Gorakhpur","Kharagpur","None"),0),
        Question("India ka biggest stadium?", arrayOf("Narendra Modi Stadium","Eden Gardens","Wankhede","None"),0),
        Question("Olympics me India ka first gold individual?", arrayOf("Abhinav Bindra","Neeraj Chopra","None","None"),0),

        Question("Neeraj Chopra kis sport se related?", arrayOf("Javelin","Cricket","Hockey","None"),0),
        Question("PV Sindhu kis sport?", arrayOf("Badminton","Tennis","Hockey","None"),0),
        Question("Sachin Tendulkar kis sport?", arrayOf("Cricket","Football","Hockey","None"),0),
        Question("Milkha Singh kis sport?", arrayOf("Athletics","Cricket","Hockey","None"),0),
        Question("Mary Kom kis sport?", arrayOf("Boxing","Wrestling","Hockey","None"),0),

        Question("ISRO ka PSLV kya hai?", arrayOf("Rocket","Satellite","Station","None"),0),
        Question("Gaganyaan mission kis se related?", arrayOf("Human spaceflight","Moon","Mars","None"),0),
        Question("NavIC kya hai?", arrayOf("Navigation system","Rocket","Satellite","None"),0),
        Question("India ka Mars mission?", arrayOf("Mangalyaan","Chandrayaan","Aditya","None"),0),
        Question("Chandrayaan-2 ka orbiter kis planet par?", arrayOf("Moon","Mars","Sun","None"),0),

        Question("India ka National Park example?", arrayOf("Kaziranga","Gir","Ranthambore","All"),3),
        Question("Project Tiger kab start hua?", arrayOf("1973","1980","1990","None"),0),
        Question("Project Elephant kis liye?", arrayOf("Elephant conservation","Tiger","Lion","None"),0),
        Question("Gir forest kis animal ke liye famous?", arrayOf("Asiatic Lion","Tiger","Elephant","None"),0),
        Question("Kaziranga kis animal ke liye famous?", arrayOf("Rhino","Tiger","Lion","None"),0)

        ,Question("भारत का पहला सौर मिशन?", arrayOf("Aditya L1","Chandrayaan","Mangalyaan","None"),0),
        Question("भारत का सबसे बड़ा IT पार्क?", arrayOf("Bangalore","Hyderabad","Pune","None"),0),
        Question("भारत का पहला स्मार्ट सिटी?", arrayOf("Bhubaneswar","Indore","Surat","None"),0),
        Question("भारत का सबसे बड़ा मंदिर परिसर?", arrayOf("Akshardham","Tirupati","Meenakshi","None"),1),
        Question("भारत का पहला मेट्रो शहर?", arrayOf("Kolkata","Delhi","Mumbai","None"),0),

        Question("भारत का पहला अंतरिक्ष यात्री?", arrayOf("Rakesh Sharma","Kalpana Chawla","Sunita Williams","None"),0),
        Question("भारत का पहला महिला अंतरिक्ष यात्री?", arrayOf("Kalpana Chawla","Sunita Williams","None","None"),0),
        Question("भारत का पहला नोबेल पुरस्कार विजेता?", arrayOf("Rabindranath Tagore","CV Raman","Mother Teresa","None"),0),
        Question("भारत का पहला टेस्ट ट्यूब बेबी?", arrayOf("Durga","Asha","Radha","None"),0),
        Question("भारत का पहला महिला राज्यपाल?", arrayOf("Sarojini Naidu","Indira Gandhi","None","None"),0),

        Question("भारत का पहला महिला मुख्यमंत्री?", arrayOf("Sucheta Kripalani","Indira Gandhi","None","None"),0),
        Question("भारत का पहला महिला लोकसभा स्पीकर?", arrayOf("Meira Kumar","Sumitra Mahajan","None","None"),0),
        Question("भारत का पहला महिला IPS?", arrayOf("Kiran Bedi","None","None","None"),0),
        Question("भारत का पहला महिला IAS?", arrayOf("Anna Rajam Malhotra","None","None","None"),0),
        Question("भारत का पहला महिला एयरलाइन पायलट?", arrayOf("Durba Banerjee","None","None","None"),0),

        Question("भारत का पहला महिला फाइटर पायलट?", arrayOf("Avani Chaturvedi","None","None","None"),0),
        Question("भारत का पहला महिला क्रिकेट कप्तान?", arrayOf("Mithali Raj","Harmanpreet","None","None"),0),
        Question("भारत का पहला महिला ओलंपिक पदक?", arrayOf("Karnam Malleswari","PV Sindhu","None","None"),0),
        Question("भारत का पहला ओलंपिक पदक?", arrayOf("Norman Pritchard","None","None","None"),0),
        Question("भारत का पहला गोल्ड ओलंपिक?", arrayOf("Abhinav Bindra","Neeraj Chopra","None","None"),0),

        Question("भारत का सबसे बड़ा रेलवे नेटवर्क?", arrayOf("Indian Railways","None","None","None"),0),
        Question("भारत का सबसे बड़ा सड़क नेटवर्क?", arrayOf("India","USA","China","None"),0),
        Question("भारत का सबसे बड़ा लोकतंत्र?", arrayOf("India","USA","China","None"),0),
        Question("भारत का सबसे बड़ा चुनाव?", arrayOf("Lok Sabha","Assembly","None","None"),0),
        Question("भारत का सबसे बड़ा बैंक?", arrayOf("SBI","PNB","BOB","None"),0),

        Question("भारत का सबसे बड़ा IT exporter?", arrayOf("TCS","Infosys","Wipro","None"),0),
        Question("भारत का सबसे बड़ा refinery?", arrayOf("Jamnagar","Mathura","Panipat","None"),0),
        Question("भारत का सबसे बड़ा steel plant?", arrayOf("Bhilai","Bokaro","Rourkela","None"),0),
        Question("भारत का सबसे बड़ा thermal plant?", arrayOf("Vindhyachal","None","None","None"),0),
        Question("भारत का सबसे बड़ा nuclear plant?", arrayOf("Kudankulam","Tarapur","None","None"),0),

        Question("भारत का सबसे बड़ा hydro plant?", arrayOf("Tehri","Bhakra","None","None"),0),
        Question("भारत का सबसे बड़ा solar plant?", arrayOf("Bhadla","Rewa","None","None"),0),
        Question("भारत का सबसे बड़ा wind farm?", arrayOf("Muppandal","None","None","None"),0),
        Question("भारत का सबसे बड़ा national park?", arrayOf("Hemis","Kaziranga","None","None"),0),
        Question("भारत का सबसे बड़ा tiger reserve?", arrayOf("Nagarjunsagar","Corbett","None","None"),0),

        Question("भारत का सबसे बड़ा desert?", arrayOf("Thar","Sahara","None","None"),0),
        Question("भारत का सबसे बड़ा island?", arrayOf("Andaman","Lakshadweep","None","None"),0),
        Question("भारत का सबसे बड़ा delta?", arrayOf("Sundarbans","None","None","None"),0),
        Question("भारत का सबसे बड़ा forest?", arrayOf("Sundarbans","Gir","None","None"),0),
        Question("भारत का सबसे बड़ा waterfall?", arrayOf("Jog Falls","None","None","None"),0),

        Question("भारत का सबसे बड़ा cave temple?", arrayOf("Ajanta","Ellora","None","None"),1),
        Question("भारत का सबसे बड़ा fort?", arrayOf("Chittorgarh","Red Fort","None","None"),0),
        Question("भारत का सबसे बड़ा palace?", arrayOf("Mysore Palace","None","None","None"),0),
        Question("भारत का सबसे बड़ा mosque?", arrayOf("Jama Masjid","None","None","None"),0),
        Question("भारत का सबसे बड़ा church?", arrayOf("Se Cathedral","None","None","None"),0),

        Question("भारत का सबसे बड़ा stadium?", arrayOf("Narendra Modi Stadium","None","None","None"),0),
        Question("भारत का सबसे बड़ा airport?", arrayOf("IGI Delhi","None","None","None"),0),
        Question("भारत का सबसे बड़ा port?", arrayOf("Kandla","Mumbai","None","None"),0),
        Question("भारत का सबसे बड़ा city population?", arrayOf("Mumbai","Delhi","None","None"),0),
        Question("भारत का सबसे बड़ा metro population?", arrayOf("Delhi NCR","Mumbai","None","None"),0)
    )

    // ⭐ Shuffle options + questions
    private fun shuffleQuestions(original: List<Question>): List<Question> {
        return original.map { q ->
            val options = q.options.toMutableList()
            val correctAnswer = options[q.correct]

            options.shuffle()
            val newCorrectIndex = options.indexOf(correctAnswer)

            Question(q.text, options.toTypedArray(), newCorrectIndex)
        }.shuffled()
    }

    private val questions by lazy { shuffleQuestions(allQuestions).take(40.coerceAtMost(allQuestions.size)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_game)

        questionText = findViewById(R.id.questionText)
        scoreText = findViewById(R.id.scoreText)
        timerText = findViewById(R.id.timerText)

        options = arrayOf(
            findViewById(R.id.op1),
            findViewById(R.id.op2),
            findViewById(R.id.op3),
            findViewById(R.id.op4)
        )

        loadQuestion()

        options.forEachIndexed { i, btn ->
            btn.setOnClickListener { checkAnswer(i) }
        }
    }

    // ⭐ TIMER
    private fun startTimer() {
        timer?.cancel()

        timer = object : CountDownTimer(timePerQuestion, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerText.text = "Time: ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                wrong++
                scoreText.text = "Right: $right  Wrong: $wrong"
                index++
                loadQuestion()
            }
        }.start()
    }

    // ⭐ LOAD QUESTION SAFE
    private fun loadQuestion() {

        if (index >= questions.size) {
            showResult()
            return
        }

        val q = questions[index]
        questionText.text = q.text

        options.forEachIndexed { i, btn ->
            btn.text = q.options[i]
            btn.visibility = Button.VISIBLE
        }

        startTimer()
    }

    // ⭐ CHECK ANSWER
    private fun checkAnswer(selected: Int) {
        timer?.cancel()

        if (index >= questions.size) return

        val q = questions[index]
        if (selected == q.correct) right++ else wrong++

        scoreText.text = "Right: $right  Wrong: $wrong"

        index++
        loadQuestion()
    }

    // ⭐ RESULT SCREEN
    private fun showResult() {
        timer?.cancel()

        questionText.text = "Quiz Finished 🎉"
        timerText.text = ""

        options.forEach { it.visibility = Button.GONE }

        scoreText.text = "Right: $right  Wrong: $wrong"
    }
}