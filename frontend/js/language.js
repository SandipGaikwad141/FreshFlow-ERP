/* =========================================================
   DPD AGRO FRESH
   MULTILINGUAL SYSTEM
   ========================================================= */

(function () {
    "use strict";

    const LANGUAGES = {
        en: {
            name: "English",
            native: "English"
        },

        mr: {
            name: "Marathi",
            native: "मराठी"
        },

        hi: {
            name: "Hindi",
            native: "हिन्दी"
        },

        kn: {
            name: "Kannada",
            native: "ಕನ್ನಡ"
        },

        te: {
            name: "Telugu",
            native: "తెలుగు"
        },

        ta: {
            name: "Tamil",
            native: "தமிழ்"
        }
    };


    /* =====================================================
       TRANSLATIONS
       ===================================================== */

    const translations = {

        /* =================================================
           COMMON NAVBAR
           ================================================= */

        "Home": {
            mr: "मुख्यपृष्ठ",
            hi: "होम",
            kn: "ಮುಖಪುಟ",
            te: "హోమ్",
            ta: "முகப்பு"
        },

        "About": {
            mr: "आमच्याबद्दल",
            hi: "हमारे बारे में",
            kn: "ನಮ್ಮ ಬಗ್ಗೆ",
            te: "మా గురించి",
            ta: "எங்களைப் பற்றி"
        },

        "Products": {
            mr: "उत्पादने",
            hi: "उत्पाद",
            kn: "ಉತ್ಪನ್ನಗಳು",
            te: "ఉత్పత్తులు",
            ta: "தயாரிப்புகள்"
        },

        "Supply & Export": {
            mr: "पुरवठा आणि निर्यात",
            hi: "आपूर्ति और निर्यात",
            kn: "ಪೂರೈಕೆ ಮತ್ತು ರಫ್ತು",
            te: "సరఫరా & ఎగుమతి",
            ta: "விநியோகம் & ஏற்றுமதி"
        },

        "Contact": {
            mr: "संपर्क",
            hi: "संपर्क करें",
            kn: "ಸಂಪರ್ಕ",
            te: "సంప్రదించండి",
            ta: "தொடர்பு"
        },

        "Admin": {
            mr: "प्रशासक",
            hi: "एडमिन",
            kn: "ನಿರ್ವಾಹಕ",
            te: "అడ్మిన్",
            ta: "நிர்வாகி"
        },


        /* =================================================
           ABOUT HERO
           ================================================= */

        "About DPD Agro Fresh": {
            mr: "DPD Agro Fresh बद्दल",
            hi: "DPD Agro Fresh के बारे में",
            kn: "DPD Agro Fresh ಬಗ್ಗೆ",
            te: "DPD Agro Fresh గురించి",
            ta: "DPD Agro Fresh பற்றி"
        },

        "Fresh Produce.": {
            mr: "ताजे कृषी उत्पादन.",
            hi: "ताज़ा कृषि उत्पाद.",
            kn: "ತಾಜಾ ಕೃಷಿ ಉತ್ಪನ್ನಗಳು.",
            te: "తాజా వ్యవసాయ ఉత్పత్తులు.",
            ta: "புதிய வேளாண் பொருட்கள்."
        },

        "Reliable Supply.": {
            mr: "विश्वसनीय पुरवठा.",
            hi: "विश्वसनीय आपूर्ति.",
            kn: "ವಿಶ್ವಾಸಾರ್ಹ ಪೂರೈಕೆ.",
            te: "విశ్వసనీయ సరఫరా.",
            ta: "நம்பகமான விநியோகம்."
        },

        "DPD Agro Fresh connects customers with a wide range of fresh vegetables, exotic vegetables, pulses and fruits.": {
            mr: "DPD Agro Fresh ग्राहकांना ताज्या भाज्या, विदेशी भाज्या, कडधान्ये आणि फळांची विस्तृत श्रेणी उपलब्ध करून देते.",
            hi: "DPD Agro Fresh ग्राहकों को ताज़ी सब्जियों, विदेशी सब्जियों, दालों और फलों की विस्तृत श्रृंखला उपलब्ध कराता है।",
            kn: "DPD Agro Fresh ಗ್ರಾಹಕರಿಗೆ ತಾಜಾ ತರಕಾರಿಗಳು, ವಿದೇಶಿ ತರಕಾರಿಗಳು, ಬೇಳೆಕಾಳುಗಳು ಮತ್ತು ಹಣ್ಣುಗಳ ವ್ಯಾಪಕ ಶ್ರೇಣಿಯನ್ನು ಒದಗಿಸುತ್ತದೆ.",
            te: "DPD Agro Fresh వినియోగదారులకు తాజా కూరగాయలు, విదేశీ కూరగాయలు, పప్పులు మరియు పండ్ల విస్తృత శ్రేణిని అందిస్తుంది.",
            ta: "DPD Agro Fresh வாடிக்கையாளர்களுக்கு புதிய காய்கறிகள், வெளிநாட்டு காய்கறிகள், பருப்பு வகைகள் மற்றும் பழங்களின் பரந்த வரிசையை வழங்குகிறது."
        },


        /* =================================================
           INTRODUCTION
           ================================================= */

        "Who We Are": {
            mr: "आम्ही कोण आहोत",
            hi: "हम कौन हैं",
            kn: "ನಾವು ಯಾರು",
            te: "మేము ఎవరు",
            ta: "நாங்கள் யார்"
        },

        "About DPD Agro Fresh": {
            mr: "DPD Agro Fresh बद्दल",
            hi: "DPD Agro Fresh के बारे में",
            kn: "DPD Agro Fresh ಬಗ್ಗೆ",
            te: "DPD Agro Fresh గురించి",
            ta: "DPD Agro Fresh பற்றி"
        },

        "DPD Agro Fresh is focused on supplying fresh produce to customers across multiple markets.": {
            mr: "DPD Agro Fresh विविध बाजारपेठांमधील ग्राहकांना ताजे कृषी उत्पादन पुरवण्यावर लक्ष केंद्रित करते.",
            hi: "DPD Agro Fresh विभिन्न बाजारों में ग्राहकों को ताज़ा कृषि उत्पाद उपलब्ध कराने पर केंद्रित है।",
            kn: "DPD Agro Fresh ವಿವಿಧ ಮಾರುಕಟ್ಟೆಗಳ ಗ್ರಾಹಕರಿಗೆ ತಾಜಾ ಕೃಷಿ ಉತ್ಪನ್ನಗಳನ್ನು ಪೂರೈಸುವತ್ತ ಗಮನಹರಿಸಿದೆ.",
            te: "DPD Agro Fresh వివిధ మార్కెట్లలోని వినియోగదారులకు తాజా వ్యవసాయ ఉత్పత్తులను సరఫరా చేయడంపై దృష్టి పెడుతుంది.",
            ta: "DPD Agro Fresh பல்வேறு சந்தைகளில் உள்ள வாடிக்கையாளர்களுக்கு புதிய வேளாண் பொருட்களை வழங்குவதில் கவனம் செலுத்துகிறது."
        },

        "Our product range includes Indian vegetables, exotic vegetables, pulses, domestic fruits and exotic fruits.": {
            mr: "आमच्या उत्पादनांमध्ये भारतीय भाज्या, विदेशी भाज्या, कडधान्ये, देशी फळे आणि विदेशी फळांचा समावेश आहे.",
            hi: "हमारी उत्पाद श्रृंखला में भारतीय सब्जियां, विदेशी सब्जियां, दालें, घरेलू फल और विदेशी फल शामिल हैं।",
            kn: "ನಮ್ಮ ಉತ್ಪನ್ನ ಶ್ರೇಣಿಯಲ್ಲಿ ಭಾರತೀಯ ತರಕಾರಿಗಳು, ವಿದೇಶಿ ತರಕಾರಿಗಳು, ಬೇಳೆಕಾಳುಗಳು, ದೇಶೀಯ ಹಣ್ಣುಗಳು ಮತ್ತು ವಿದೇಶಿ ಹಣ್ಣುಗಳು ಸೇರಿವೆ.",
            te: "మా ఉత్పత్తుల శ్రేణిలో భారతీయ కూరగాయలు, విదేశీ కూరగాయలు, పప్పులు, దేశీయ పండ్లు మరియు విదేశీ పండ్లు ఉన్నాయి.",
            ta: "எங்கள் தயாரிப்பு வரிசையில் இந்திய காய்கறிகள், வெளிநாட்டு காய்கறிகள், பருப்பு வகைகள், உள்நாட்டு பழங்கள் மற்றும் வெளிநாட்டு பழங்கள் அடங்கும்."
        },

        "Built Around Fresh Produce": {
            mr: "ताज्या कृषी उत्पादनाभोवती उभारलेले",
            hi: "ताज़े कृषि उत्पादों पर आधारित",
            kn: "ತಾಜಾ ಕೃಷಿ ಉತ್ಪನ್ನಗಳ ಸುತ್ತ ನಿರ್ಮಿಸಲಾಗಿದೆ",
            te: "తాజా వ్యవసాయ ఉత్పత్తులపై ఆధారితం",
            ta: "புதிய வேளாண் பொருட்களை மையமாகக் கொண்டது"
        },

        "Explore Products": {
            mr: "उत्पादने पहा",
            hi: "उत्पाद देखें",
            kn: "ಉತ್ಪನ್ನಗಳನ್ನು ನೋಡಿ",
            te: "ఉత్పత్తులను చూడండి",
            ta: "தயாரிப்புகளைப் பார்க்கவும்"
        },


        /* =================================================
           STATS
           ================================================= */

        "Customers": {
            mr: "ग्राहक",
            hi: "ग्राहक",
            kn: "ಗ್ರಾಹಕರು",
            te: "వినియోగదారులు",
            ta: "வாடிக்கையாளர்கள்"
        },

        "Main Product Categories": {
            mr: "मुख्य उत्पादन श्रेणी",
            hi: "मुख्य उत्पाद श्रेणियां",
            kn: "ಮುಖ್ಯ ಉತ್ಪನ್ನ ವರ್ಗಗಳು",
            te: "ప్రధాన ఉత్పత్తి వర్గాలు",
            ta: "முக்கிய தயாரிப்பு வகைகள்"
        },

        "Supply Markets": {
            mr: "पुरवठा बाजारपेठा",
            hi: "आपूर्ति बाजार",
            kn: "ಪೂರೈಕೆ ಮಾರುಕಟ್ಟೆಗಳು",
            te: "సరఫరా మార్కెట్లు",
            ta: "விநியோக சந்தைகள்"
        },


        /* =================================================
           PRODUCT RANGE
           ================================================= */

        "Our Range": {
            mr: "आमची उत्पादन श्रेणी",
            hi: "हमारी उत्पाद श्रृंखला",
            kn: "ನಮ್ಮ ಉತ್ಪನ್ನ ಶ್ರೇಣಿ",
            te: "మా ఉత్పత్తుల శ్రేణి",
            ta: "எங்கள் தயாரிப்பு வரிசை"
        },

        "What We Supply": {
            mr: "आम्ही काय पुरवतो",
            hi: "हम क्या आपूर्ति करते हैं",
            kn: "ನಾವು ಏನು ಪೂರೈಸುತ್ತೇವೆ",
            te: "మేము ఏమి సరఫరా చేస్తాము",
            ta: "நாங்கள் என்ன வழங்குகிறோம்"
        },

        "Explore the different categories available through DPD Agro Fresh.": {
            mr: "DPD Agro Fresh मार्फत उपलब्ध विविध उत्पादन श्रेणी जाणून घ्या.",
            hi: "DPD Agro Fresh के माध्यम से उपलब्ध विभिन्न उत्पाद श्रेणियों को देखें।",
            kn: "DPD Agro Fresh ಮೂಲಕ ಲಭ್ಯವಿರುವ ವಿವಿಧ ಉತ್ಪನ್ನ ವರ್ಗಗಳನ್ನು ಅನ್ವೇಷಿಸಿ.",
            te: "DPD Agro Fresh ద్వారా అందుబాటులో ఉన్న వివిధ ఉత్పత్తి వర్గాలను చూడండి.",
            ta: "DPD Agro Fresh மூலம் கிடைக்கும் பல்வேறு தயாரிப்பு வகைகளை ஆராயுங்கள்."
        },

        "Exotic Vegetables": {
            mr: "विदेशी भाज्या",
            hi: "विदेशी सब्जियां",
            kn: "ವಿದೇಶಿ ತರಕಾರಿಗಳು",
            te: "విదేశీ కూరగాయలు",
            ta: "வெளிநாட்டு காய்கறிகள்"
        },

        "Indian Vegetables": {
            mr: "भारतीय भाज्या",
            hi: "भारतीय सब्जियां",
            kn: "ಭಾರತೀಯ ತರಕಾರಿಗಳು",
            te: "భారతీయ కూరగాయలు",
            ta: "இந்திய காய்கறிகள்"
        },

        "Pulses": {
            mr: "कडधान्ये",
            hi: "दालें",
            kn: "ಬೇಳೆಕಾಳುಗಳು",
            te: "పప్పులు",
            ta: "பருப்பு வகைகள்"
        },

        "Domestic Fruits": {
            mr: "देशी फळे",
            hi: "घरेलू फल",
            kn: "ದೇಶೀಯ ಹಣ್ಣುಗಳು",
            te: "దేశీయ పండ్లు",
            ta: "உள்நாட்டு பழங்கள்"
        },

        "Exotic & Foreign Fruits": {
            mr: "विदेशी आणि आयातीत फळे",
            hi: "विदेशी और आयातित फल",
            kn: "ವಿದೇಶಿ ಮತ್ತು ಆಮದು ಹಣ್ಣುಗಳು",
            te: "విదేశీ మరియు దిగుమతి పండ్లు",
            ta: "வெளிநாட்டு மற்றும் இறக்குமதி பழங்கள்"
        },


        /* =================================================
           INFRASTRUCTURE
           ================================================= */

        "Our Infrastructure": {
            mr: "आमची पायाभूत सुविधा",
            hi: "हमारा बुनियादी ढांचा",
            kn: "ನಮ್ಮ ಮೂಲಸೌಕರ್ಯ",
            te: "మా మౌలిక సదుపాయాలు",
            ta: "எங்கள் உள்கட்டமைப்பு"
        },

        "From Our Company": {
            mr: "आमच्या कंपनीतून",
            hi: "हमारी कंपनी से",
            kn: "ನಮ್ಮ ಕಂಪನಿಯಿಂದ",
            te: "మా కంపెనీ నుండి",
            ta: "எங்கள் நிறுவனத்திலிருந்து"
        },

        "Company & Warehouse Photos": {
            mr: "कंपनी आणि गोदामाचे फोटो",
            hi: "कंपनी और वेयरहाउस की तस्वीरें",
            kn: "ಕಂಪನಿ ಮತ್ತು ಗೋದಾಮಿನ ಫೋಟೋಗಳು",
            te: "కంపెనీ మరియు గిడ్డంగి ఫోటోలు",
            ta: "நிறுவனம் மற்றும் கிடங்கு புகைப்படங்கள்"
        },

        "Client-provided photographs will be added here.": {
            mr: "ग्राहकाकडून मिळालेले फोटो येथे जोडले जातील.",
            hi: "ग्राहक द्वारा उपलब्ध कराई गई तस्वीरें यहां जोड़ी जाएंगी।",
            kn: "ಗ್ರಾಹಕರು ಒದಗಿಸಿದ ಫೋಟೋಗಳನ್ನು ಇಲ್ಲಿ ಸೇರಿಸಲಾಗುತ್ತದೆ.",
            te: "కస్టమర్ అందించిన ఫోటోలు ఇక్కడ జోడించబడతాయి.",
            ta: "வாடிக்கையாளர் வழங்கும் புகைப்படங்கள் இங்கே சேர்க்கப்படும்."
        },


        /* =================================================
           LOCATIONS
           ================================================= */

        "Our Reach": {
            mr: "आमची पोहोच",
            hi: "हमारी पहुंच",
            kn: "ನಮ್ಮ ವ್ಯಾಪ್ತಿ",
            te: "మా పరిధి",
            ta: "எங்கள் சென்றடைவு"
        },

        "Supply Locations": {
            mr: "पुरवठा ठिकाणे",
            hi: "आपूर्ति स्थान",
            kn: "ಪೂರೈಕೆ ಸ್ಥಳಗಳು",
            te: "సరఫరా ప్రాంతాలు",
            ta: "விநியோக இடங்கள்"
        },

        "DPD Agro Fresh supplies products across several cities and markets.": {
            mr: "DPD Agro Fresh अनेक शहरे आणि बाजारपेठांमध्ये उत्पादने पुरवते.",
            hi: "DPD Agro Fresh कई शहरों और बाजारों में उत्पादों की आपूर्ति करता है।",
            kn: "DPD Agro Fresh ಹಲವಾರು ನಗರಗಳು ಮತ್ತು ಮಾರುಕಟ್ಟೆಗಳಿಗೆ ಉತ್ಪನ್ನಗಳನ್ನು ಪೂರೈಸುತ್ತದೆ.",
            te: "DPD Agro Fresh అనేక నగరాలు మరియు మార్కెట్లకు ఉత్పత్తులను సరఫరా చేస్తుంది.",
            ta: "DPD Agro Fresh பல நகரங்கள் மற்றும் சந்தைகளுக்கு தயாரிப்புகளை வழங்குகிறது."
        },


        /* =================================================
           CTA
           ================================================= */

        "Ready to Explore?": {
            mr: "अन्वेषणासाठी तयार आहात?",
            hi: "जानने के लिए तैयार हैं?",
            kn: "ಅನ್ವೇಷಿಸಲು ಸಿದ್ಧರಿದ್ದೀರಾ?",
            te: "అన్వేషించడానికి సిద్ధంగా ఉన్నారా?",
            ta: "ஆராய தயாரா?"
        },

        "Find Fresh Produce For Your Requirement": {
            mr: "तुमच्या गरजेसाठी ताजे कृषी उत्पादन शोधा",
            hi: "अपनी आवश्यकता के लिए ताज़ा कृषि उत्पाद खोजें",
            kn: "ನಿಮ್ಮ ಅಗತ್ಯಕ್ಕಾಗಿ ತಾಜಾ ಕೃಷಿ ಉತ್ಪನ್ನಗಳನ್ನು ಹುಡುಕಿ",
            te: "మీ అవసరానికి తాజా వ్యవసాయ ఉత్పత్తులను కనుగొనండి",
            ta: "உங்கள் தேவைக்கான புதிய வேளாண் பொருட்களை கண்டறியுங்கள்"
        },

        "View Products": {
            mr: "उत्पादने पहा",
            hi: "उत्पाद देखें",
            kn: "ಉತ್ಪನ್ನಗಳನ್ನು ನೋಡಿ",
            te: "ఉత్పత్తులను చూడండి",
            ta: "தயாரிப்புகளைப் பார்க்கவும்"
        },

        "Contact Us": {
            mr: "संपर्क करा",
            hi: "संपर्क करें",
            kn: "ಸಂಪರ್ಕಿಸಿ",
            te: "సంప్రదించండి",
            ta: "தொடர்பு கொள்ளுங்கள்"
        },


        /* =================================================
           FOOTER
           ================================================= */

        "Quick Links": {
            mr: "जलद दुवे",
            hi: "त्वरित लिंक",
            kn: "ತ್ವರಿತ ಲಿಂಕ್‌ಗಳು",
            te: "త్వరిత లింకులు",
            ta: "விரைவு இணைப்புகள்"
        },

        "Contact": {
            mr: "संपर्क",
            hi: "संपर्क",
            kn: "ಸಂಪರ್ಕ",
            te: "సంప్రదించండి",
            ta: "தொடர்பு"
        }
    };


    /* =====================================================
       NORMALIZE TEXT
       ===================================================== */

    function normalizeText(text) {
        return text
            .replace(/\s+/g, " ")
            .trim();
    }


    /* =====================================================
       TRANSLATE ELEMENT
       ===================================================== */

    function translateElement(element, language) {

        const key = element.dataset.i18n;

        if (!key) return;

        const translation =
            translations[key] &&
            translations[key][language];

        if (!translation) return;

        element.textContent = translation;
    }


    /* =====================================================
       APPLY LANGUAGE
       ===================================================== */

    function applyLanguage(language) {

        if (!LANGUAGES[language]) {
            language = "en";
        }

        document.documentElement.lang = language;

        localStorage.setItem(
            "dpd-agro-language",
            language
        );

        document
            .querySelectorAll("[data-i18n]")
            .forEach(function (element) {
                translateElement(element, language);
            });

        updateLanguageSelector(language);
    }


    /* =====================================================
       LANGUAGE SELECTOR
       ===================================================== */

    function createLanguageSelector() {

        const navbar = document.querySelector(
            "#mainNavbarCollapse .navbar-nav"
        );

        if (!navbar) return;

        if (document.getElementById("languageSelectorItem")) {
            return;
        }

        const li = document.createElement("li");

        li.className =
            "nav-item dropdown ms-lg-2";

        li.id = "languageSelectorItem";

        li.innerHTML = `
            <a
                class="nav-link dropdown-toggle language-selector-link"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false">

                <i class="bi bi-translate"></i>
                <span id="currentLanguageName">English</span>

            </a>

            <ul class="dropdown-menu dropdown-menu-end language-dropdown">

                ${Object.entries(LANGUAGES)
                    .map(([code, lang]) => `
                        <li>
                            <button
                                type="button"
                                class="dropdown-item language-option"
                                data-language="${code}">

                                <span>${lang.native}</span>

                                <small>${lang.name}</small>

                            </button>
                        </li>
                    `)
                    .join("")}

            </ul>
        `;

        navbar.appendChild(li);


        li.querySelectorAll(".language-option")
            .forEach(function (button) {

                button.addEventListener(
                    "click",
                    function () {

                        const language =
                            this.dataset.language;

                        applyLanguage(language);
                    }
                );

            });
    }


    /* =====================================================
       UPDATE SELECTOR
       ===================================================== */

    function updateLanguageSelector(language) {

        const current =
            document.getElementById(
                "currentLanguageName"
            );

        if (!current) return;

        current.textContent =
            LANGUAGES[language].native;

        document
            .querySelectorAll(".language-option")
            .forEach(function (button) {

                button.classList.toggle(
                    "active",
                    button.dataset.language === language
                );

            });
    }


    /* =====================================================
       DETECT LANGUAGE
       ===================================================== */

    function detectLanguage() {

        const saved =
            localStorage.getItem(
                "dpd-agro-language"
            );

        if (saved && LANGUAGES[saved]) {
            return saved;
        }

        const browserLanguage =
            navigator.language
                .toLowerCase()
                .split("-")[0];

        if (LANGUAGES[browserLanguage]) {
            return browserLanguage;
        }

        return "en";
    }


    /* =====================================================
       INITIALIZE
       ===================================================== */

    document.addEventListener(
        "DOMContentLoaded",
        function () {

            createLanguageSelector();

            const language =
                detectLanguage();

            applyLanguage(language);

        }
    );


    /* =====================================================
       PUBLIC API
       ===================================================== */

    window.DPDAgroLanguage = {

        change: function (language) {
            applyLanguage(language);
        },

        current: function () {
            return localStorage.getItem(
                "dpd-agro-language"
            ) || "en";
        }

    };

})();