function getData(url,call,par){
    $.ajax({
        url: url,
        data:par||'{}',
        dataType:"json",
        headers: {
            "Content-Type": "application/json"
        },
        async : false,
        type: "POST",
        success: function(data){
            var code=data.code||data.status;
            if (code == 200) {
                call(data.data);
            } else {
                var meaages=data.message||data.error;
                alert(meaasge);
            }
        }
    });
}
/*var table=[
    {"id":"1","review_text":"Cannot get account on line to go through I have act code Apple I pad I only have apple I pad Not sure Ok will phone Adrian flux and get document sent in post. Thank you.","sentiment_ntlk":"Negative","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"thank (0.03) appl (0.02) pad (0.02)","chat_record_id":"635385"},
    {"id":"2","review_text":"I have been a Mitel engineer for over 20 years and never heard of talkative you are big in Mitel I see you have the Bravissimo account that use to be a 4Sight customer you don't have Mitel engineers the online stuff for Ignite you have made is very good Mitel don't have anything like it I have made my own but not as good as yours but you outsource this have you got AI working and if so which customer and can I check out the bot online I am looking to set up a demo but not getting any feedback from the field so I imagine its not that easy never heard of them Olive and 4Sight are two of the best in the UK but not your guys do you know ALC from Mitel he is working on AI for Olive I am trying to reach out to other that have started the journey as Mitel documention not that good do you get that stuff? I was looking for a new challenge. I will dig into it do you start with coursera or do I need to get demo up and running first??? thanks I better got on with some work now. but well done on the site. you need to get it out there on linkdin as I have not seen you. john smith nice one  thanks bye","sentiment_ntlk":"Positive","sentiment_flair":"POSITIVE","sentiment_bert":"NEGATIVE","topic":"thank (0.02) talk (0.02) chat (0.01)","chat_record_id":"642035"},
    {"id":"3","review_text":"Hello in reference to this https://support.talkative.uk/technical/adding-scripts page we have the script implemented for a client However this CSS file https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css seems to have no other dependencies on the page and it is found to be 100% unused across page templates for our client Could this line of code \"\"<link rel=\"\"stylesheet\"\" href=\"\"https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css\"\">\"\" be removed from the script as it contributes to the site loading time but doesn't seem to be contributing much to the page? Ok thank you. Can you give us a screenshot example of what the missing icons would be and how this would look in practice? Happy to take this over to email or a phone call if that would be easier Is the one on the right with and the one on the left without? Oh right I see  so essentially it is just the chat bubble? & removing the font awesome line would also remove these other icons you mention? Thank you so much :) this is super helpful. I think this is it thank you","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"thank (0.04) chat (0.02) function (0.01)","chat_record_id":"645620"},
    {"id":"4","review_text":"Hi Felix. I thin k I spoke to you the other day about this same issue. Regarding https://support.talkative.uk/technical/adding-scripts and removing the line of script <link rel=\"\"stylesheet\"\" href=\"\"https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css\"\"> to improve site performance foo@bar.com ABC Sure - that would be great thanks would you be able to just ping me when you have forwarded the email chain? Ah I've got it  thanks very much!","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"POSITIVE","topic":"thank (0.02) talk (0.02) chat (0.01)","chat_record_id":"651668"},
    {"id":"5","review_text":"Hi Steve How are you? My name is Glenn Walker. I was just wondering  do you think football would be better if the quadrant for corner kicks was bigger? Hopefully on this evening's Spurs match. I'm looking for some jazzy ideas so I don't sound so boring? Ah okay. Thanks for the kind feedback. You have a good day Steve. Good talking with you. Nope. I think I'm good from here on. Thank you.","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"POSITIVE","topic":"thank (0.03) good (0.02) think (0.02)","chat_record_id":"654874"},
    {"id":"6","review_text":"I am shopping around for a live chat service for the College i work for. We would like multiple live chat options for different webpages while also integrating with Salesforce Awesome sounds good so far Regarding your pricing options  does 3 user limit to how many chat boxes we can have? at least 7 different chat boxes So will the user just have to be logged into their Salesforce account to interact in the chat boxes? Okay cool","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"POSITIVE","topic":"chat (0.03) live (0.01) box (0.01)","chat_record_id":"663861"},
    {"id":"7","review_text":"HI My name is Paul I am Head of Sales for company-name.co.uk We would like to take the Free Trial During the Trial can i connect to Mitel Ignite? perfect thank you. <email> Thank you  the guide will be useful  I will send this over to our IT team Ok. I have not received the email yet  let me know when its sent. Thank you I will speak with out IT and Web team and get the Trial going asap thanks for your help today.","sentiment_ntlk":"Positive","sentiment_flair":"POSITIVE","sentiment_bert":"POSITIVE","topic":"thank (0.03) chat (0.03) need (0.01)","chat_record_id":"672991"},
    {"id":"8","review_text":"of how a customer see a cobrowsing session we can cobrowse now so i can see it ok  how exaclty i can see your cursor for example is a bit difficult to see the difference yes but i need to see how a customer will see it oh  ok i see it great  thanks thats all what i need have a good day","sentiment_ntlk":"Positive","sentiment_flair":"POSITIVE","sentiment_bert":"POSITIVE","topic":"thank (0.02) need (0.02) agent (0.01)","chat_record_id":"673143"},
    {"id":"9","review_text":"hi Steve are you still there I am a contractor for a company that is using Mitel and Ignite and we are looking at options for chat and came across the talkative but a few people are keen to use Zendesk Hospitality sector - i might get a demo as it does look good  -but I need to do a summary of features and benefits should I take your details and follow up <email> thanks ok cool I noticed your comartive section on the website but noticed that each one is not always comparing like with like e.g. data security is not listed on the LIveperson comparison but is on the Livechat good to know as you are in the UK - what is the support provided?  Would it be through the partner that we have in Melbourne? thanks Steve i am trying to scroll back to a part of the chat as you mentioned something earlier and I did not read the full note is there a way I can go back to begining of chat it does not seem to be letting me yes I do have a scroll bar but it keeps jumping ahead it is ok - i found it - it was just in regards to combining chat with the email and voice from Mitel I am not sure they would want to change the ignite voice and email - they are just not super sold on the Ignite chat functionality so wanted to look at options let me know when you send through the mail and I will let you know Mitel chat tool yes that is what I thought    We would look to keep the email and voice component and look to use the chat and other social media integrations yes definitely need to arrange and demo I think probably some time next week - will need to confirm  times how long would a demo take? ok thanks thanks a lot Steve i did wonder what was happening but then went off and did a few other things so not problem but it would be good to now how it happened.  I noticed that it say I was the next in queue but it does not mention time.  Is that an option to set up? just an estimate wait time? ok - thanks technology .... never 100% perfect :) stay safe and well and I will be in touch soon lol you too - thanks a bunch for all your help today","sentiment_ntlk":"Positive","sentiment_flair":"POSITIVE","sentiment_bert":"POSITIVE","topic":"thank (0.03) good (0.02) think (0.02)","chat_record_id":"679458"},
    {"id":"10","review_text":"Sprechen Sie deutsch? Sind Sie ei nBot? Auch Bots können Namen haben. Fängt der Live Chat mit einem Bot an? Das ist leider schlecht. Vielen Dank.","sentiment_ntlk":"Neutral","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"agent (0.03) ye (0.02) access (0.01)","chat_record_id":"681386"},
    {"id":"11","review_text":"Hi Steve!!! Thanks for your help I want to try all your functionality integrated with my salesforce Live chat call sharing co browsing for our online journey experience Is possible to do that ? Can we have a demo? So fast as you can do We want to integrate with our website salesforce an our App company-site.com Investment Maybe we can have a video call? Sure  hold on please May be 2:00 pm Mexico Time? Thursday? <email> Perfect!!! In advanced it is possible to get a Qoute for 30 advisors? Thank you!! I don't have it yet  but I gess is on the way Richt  I understand  works for me!!! thank you!!! I appreciate your help!!! Same to you!!! Have a nice day!!! Sure","sentiment_ntlk":"Positive","sentiment_flair":"POSITIVE","sentiment_bert":"POSITIVE","topic":"thank (0.02) need (0.02) agent (0.01)","chat_record_id":"682477"},
    {"id":"12","review_text":"hmp ok I'll give it an other try yea I checked my spam folder (it's managed by google) hmp it worked that time **** I see what happen I used this link https://engage.talkative.uk/login/sso instead of the link you provided - why is there 2 different urls? ok yea just very confusing to new users ** because I don't see a link to that page from the talkative site o well Thank you for your time! have a great day!","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"thank (0.02) talk (0.02) chat (0.01)","chat_record_id":"690453"},
    {"id":"13","review_text":"Hi I have signed up for a free trial will the details come through via email on how to get started? <email> Yes. I did have another question. If you just want chat or wanted chat and video call do you need to place different code in your site  or do you place the same code  but change something in a backend control panel to chat what the visitor has access too? Sorry should have ready to change what the visitor has access too? I have limited access to the code on the site. but it will be a direct code injection. I have created a virtual experience and there is only one html page that everything runs in. Hence requesting a trial  so I can test out what can be done with what I have access too. The software I use creates the html for me  so I will have to break the code. Are you able to direct me to a tutorial or instruction of the script and where it sits on the webpage? Im guessing it either sits in the header of footer???","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"thank (0.02) talk (0.02) chat (0.01)","chat_record_id":"691563"},
    {"id":"14","review_text":"a potential customer yes planning to come up with one also want a document or brochure that i can present to my line manager please <email> will really appreciate ok noted just a moment am just reading thro the doc do you have a more detailed technical proposal thanks Steve let me go through shared info much appreciated if i need a demo will come back have a good day too bye for now","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"POSITIVE","topic":"thank (0.02) need (0.01) want (0.01)","chat_record_id":"730006"},
    {"id":"15","review_text":"Hi I just wanted to ask about your service and wanted to know if you offer a portal/dashboard for users to connect to agents via video chat with native sdks iOS & android Yes SureSo my question is possible Right and users can connect to agents that are online and you're sdk will tell us agents that are available etc? Ok but if there are no agents available it would tell us so we can handle this in our code? Sweet So I have a few more reqs do you following the following * Data centre location – must be in the UK \"*  TLS protocol\" \"*  Database encryption – AES 256bit required Perfect\"  so your dashboard where you can see people to connect too is this a separate portal and can you invite multiple agents I mean that lets say I have two agents  they can both access the portal and manage/connect to users who are looking to video chat Yh but agents can see who is waiting and connect to them via the dashboard/portal? I'm a bit confused. Do you have a contact number so we could possibly discuss this on call maybe? So you're not free now? perfect <email>","sentiment_ntlk":"Positive","sentiment_flair":"POSITIVE","sentiment_bert":"NEGATIVE","topic":"agent (0.03) ye (0.02) access (0.01)","chat_record_id":"733264"},
    {"id":"16","review_text":"I am good you tell. I need some assitane about this talkative tool i need to have demo right now so i can know how this things work on website As we are startup agency and we want a chat system that we can communicate with customer in different language waleed <url> here is the link its a digital agency we want to have connection with customers but they need to know that we are talking in same language but we are in english and they will recieve it in german and when they sent message in german we will receive it in english this what i wanted can you show me the demo that how this thing work sure sure <email>","sentiment_ntlk":"Positive","sentiment_flair":"POSITIVE","sentiment_bert":"NEGATIVE","topic":"thank (0.03) chat (0.03) need (0.01)","chat_record_id":"737476"},
    {"id":"17","review_text":"Hi do you have a check list comparison with the Mitel standard chat function?  Like to see what additional functions we would get. Mat Test <email> The comparison with Live Chat is helpful  but would be good to see comparison with Mitel - which is what we currently have.  Thanks Ah thanks Steve - couldn't tell from the photo whether that was you!  Sorry and many thanks. Thanks  you to","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"POSITIVE","topic":"thank (0.04) chat (0.02) function (0.01)","chat_record_id":"738220"},
    {"id":"18","review_text":"Hi Steve My name's Simon Smith and email is <email> Just the default queue Ah okay I wasn't online. That's sorted it. Thank you! That's great heers! While I have you to what level can we edit the chat window widget please? Is it possible to use CSS overrides and replace buttons? Thanks Steve. That's perfect Next week would be great. Appreciate your help Cheers  enjoy your Easter weekend!","sentiment_ntlk":"Positive","sentiment_flair":"POSITIVE","sentiment_bert":"POSITIVE","topic":"chat (0.03) live (0.01) box (0.01)","chat_record_id":"759028"},
    {"id":"19","review_text":"Hi Steve no worries I have several questions about talkative is it possible to just have the call function on the widget? not allowing customers to chat but only call? nice! can we add fields to the form before the client can connect with us? amazing! do you have Salesforce integration? thanks! I have a couple more other questions how customizable is the widget? and is it available for all countries? great news! last question before I keep on doing my research on your oage do the calls get directed into Salesforce to the agents or calls are made through your own interface/tool? great! would it be possible for me to see how that looks like? that would be great! I have scheduled one before speaking with you for today at 14.30 not sure if that would work with Felix Winstone would you be able to speak with him or join us? awesome  thanks! sounds good  thanks for everything!","sentiment_ntlk":"Positive","sentiment_flair":"POSITIVE","sentiment_bert":"POSITIVE","topic":"thank (0.03) chat (0.03) need (0.01)","chat_record_id":"781287"},
    {"id":"20","review_text":"do you provide multi agent facility? yes is that support in video chat ? may 5 - 10 agents is that video chat with multi agent? we have requirement if our one agent is busy with one customer  then we need to transfer next video query to another agent by automatically. is that possible? yes  we interested in trial how cal i rdeem that ok name is Ajay <email> can i change the mail id? i lost the access with that id <email> let me check CAN U PLEASE REDIRECT IT TO <email> are u done? yes i have access to <email> <email> ok hi now im in the dashboard could you please give me a user support","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"agent (0.03) ye (0.02) access (0.01)","chat_record_id":"804161"},
    {"id":"21","review_text":"I would to test the freetrail I copied the script in html page but its not working what are those two reasons okay how can i signed agent If I click setting tab its going to offline settings tab badge icon is not displaying I copied this code in my html page <link rel=\"\"stylesheet\"\" href=\"\"https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css\"\"><link rel=\"\"stylesheet\"\" href=\"\"https://files.talkative.uk/1.15.2/talkative-engage.css\"\"><talkative-engage id=\"\"talkative-engage\"\" company-uuid=\"\"123e4567-e89b-12d3-a456-426655440000\"\" queue-uuid=\"\"123e4567-e89b-12d3-a456-426655440000\"\" primary-color=\"\"24917661\"\" :voice=true :ga=true :email=true :chat=true :offline-email=true :callout=true position=\"\"right: 10%\"\" :content-strings=\"\"{ mainTitle: { offline: 'Please leave us a message'  default: 'Talk to us' }  options: { description: 'Click to talk with us!'}  cobrowse: {description: 'Get in touch and tell us the following reference number to start cobrowsing:'}  callout:{line1:'Want to' line2:'learn more?'}}\"\"></talkative-engage> <script src=\"\"https://files.talkative.uk/1.15.2/talkative-engage.js\"\"></script> okay is nay differences from your code and my code yes is translation will happen automatically I am typing the data in english and user typing the data in spanish Will translation will happen automatically thank you for your help its helped me a lot","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"ye (0.02) chat (0.02) agent (0.02)","chat_record_id":"820648"},
    {"id":"22","review_text":"I copied the script And added in html page You mean you need scriot waht I have copied Script","sentiment_ntlk":"Neutral","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"chat (0.03) live (0.01) box (0.01)","chat_record_id":"822186"},
    {"id":"23","review_text":"Yes Its copied from your webiste okay Please share to me Yes I lost that code","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"ye (0.02) chat (0.02) agent (0.02)","chat_record_id":"822196"},
    {"id":"24","review_text":"I would like to order some legging and have them hemmed right away. I am leaving for vacation soon.  I don’t have time to go to the store. How can this be done? How can I talk to someone live","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"chat (0.03) live (0.01) box (0.01)","chat_record_id":"843773"},
    {"id":"25","review_text":"I want to try talkative to make POC it's and insurance portal and feature I want is video chat cobrowsing pls mail it on <email> ok thanks","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"POSITIVE","topic":"thank (0.03) chat (0.03) need (0.01)","chat_record_id":"877622"},
    {"id":"26","review_text":"Yes thank you for reply me I have bought talkative and i want to intergtrae that to my website script genration part and implementaion is done but i want to intro message to show with different categories such as registration deposit How can i do that? https://eu-engage-app.s3.eu-west-2.amazonaws.com/files/f38d364d-0667-46fe-b389-5a1f5ff5c5f2 We are from casino yes How long will it take to response this is urgent i will send a mail Ok thank you","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"thank (0.02) need (0.01) want (0.01)","chat_record_id":"909227"},
    {"id":"27","review_text":"I want to resell yours services to my costumers. Company i cover the northweast of brazil. I´m prospecting Vlogers  influencers  small food business I´m a software engineer and found a consulting company I already work with a lot of SaaS solution and BaaS ok u too","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"agent (0.03) ye (0.02) access (0.01)","chat_record_id":"922223"},
    {"id":"28","review_text":"I was wondering if I could talk to Simon? No worries if not I do Wong Its about chicken kievs Its okay if he isn't available No worries Thanks for your time Have a nice day","sentiment_ntlk":"Negative","sentiment_flair":"NEGATIVE","sentiment_bert":"POSITIVE","topic":"thank (0.03) chat (0.03) need (0.01)","chat_record_id":"929567"},
    {"id":"29","review_text":"<email> Hello Steve My name is Evelyn Smith I just wondering if Talkative has the function \"\"FIeld Masking\"\"? for cobrowsing yes thanks for your support I'll check the docs not yet Ok thanks Not for now thanks bye","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"thank (0.04) chat (0.02) function (0.01)","chat_record_id":"936820"},
    {"id":"30","review_text":"you mean webapp accessible from URL? No native apps Android and iOS? I'll try it by myself tnx yes I have a free trial account","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"agent (0.03) ye (0.02) access (0.01)","chat_record_id":"945666"},
    {"id":"31","review_text":"Can you see my preious message? Oh I see I have a luxury jeweller client who needs a chat video call and booking system ideally in one. For virtual product demo and in store bookings etc Integrating with something liek calendly would be amazing Oh amazing! That sounds very exciting. <email> and I will suggest this to my client :) Thats it for now thank you!","sentiment_ntlk":"Positive","sentiment_flair":"POSITIVE","sentiment_bert":"POSITIVE","topic":"thank (0.03) appl (0.02) pad (0.02)","chat_record_id":"947367"},
    {"id":"32","review_text":"Hi Steve I'm an existing customer of Talkative and i'm looking to increase the range of implementations that I can run could you tell me if Talkative supports APIs or Integration into mobile Apps? chat and the option to pivot to voice (over the internet) would be fantastic unlikely to be video for day 1 though perfect! thanks Steve nope that's fine - If you can just supply any frameworks or app based integrations that you support that will be super useful","sentiment_ntlk":"Positive","sentiment_flair":"POSITIVE","sentiment_bert":"POSITIVE","topic":"thank (0.03) appl (0.02) pad (0.02)","chat_record_id":"949789"},
    {"id":"33","review_text":"zoom does not work you can send pls a google link","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"thank (0.03)  chat (0.03) need (0.01)","chat_record_id":"974295"},
    {"id":"34","review_text":"I want calling solution on my website Its on magento No its <url> How much time it will take to fix it We need it urgent whats your price plan for this Ok great we can start it by one then will increase Can we have a short call ? You me and my developer… Thats ok I saw this ??","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"thank (0.04) chat (0.02) function (0.01)","chat_record_id":"977333"},
    {"id":"35","review_text":"I ned to talk on saturday with my developer and hopefully we need to integrate it on saturday. will this be possible? perfect What do you need from my side? <url> its our website on Magento ok my developer is there <email> so all detail will be there like code  or API ? In case if we need anything in order to integrate  so who will be the point of contact from your site? thats great we are in urgency actually. We have to get this solution ASAP. we will discuss price on Monday. no problrm on tuesday problem* sounds good. Have you sent email to Jamie? ok waiting Also I appreciate your prompt response Steve. Yes we want audio and video both. also send detail procedure for magento 2  so he will not face any problem to integrate sounds good. Have you sent email? ok let me confirm with Jamie received or not. Yes he received and working on it Thank you If I need any thing I will contact to support. Have a good weekend  and holidays Steve. he is working on it. what is newer self service way? what will be the benefits? oh great we would love to have this. please inform this thing to Jamie as well. I will also discuss this with him. can I ask one thing? If  we take your chat and calling both solution  so let;s say a user come and click on call button now how our representative will get to know that there is a call? any mobile app you have? so there is necessary to have app to call? ok its standard i think all must have these I downloaded your mobile app its not getting credentials which i created. ok","sentiment_ntlk":"Positive","sentiment_flair":"POSITIVE","sentiment_bert":"POSITIVE","topic":"agent (0.03) ye (0.02) access (0.01)","chat_record_id":"977444"},
    {"id":"36","review_text":"Hola have you support in spanish? Ok gracias Estoy buscando un software que me ayude a conectar un QR con una videollamada inicialmente tengo un grupo de agentes que corresponden a una categoría y estas categorías están definidas por el QR esto es posible con su solución? don't worry perfecto que tiempo tardaria en implementarlo y cual seria el costo de este servicio? mas adelante me gustaria conectarlo a las paginas web quisiera tener inicialmente 5 agentes  pero este proyecto puede llegar a tener 200 o un poco mas y podría tener reportes de productividad de los agentes? si sin problema perfecto eso incluiria lo del QR? En cuanto tiempo puedo tener la implementación? es posible en menos tiempo? tenemos que lanzar un piloto lo mas pronto posible perfecto  muchas gracias Felix  quedo al pendiente agradezco la atencion  bonito dia Have a nice day","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"thank (0.02) need (0.02) agent (0.01)","chat_record_id":"985119"},
    {"id":"37","review_text":"Ok that's the information I wanted thank you! One more question does it have any certificates such as ISO 27001? Security certificates Ok  thank you very much Copy that","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"POSITIVE","topic":"thank (0.03) appl (0.02) pad (0.02)","chat_record_id":"996153"},
    {"id":"38","review_text":"Hi Felix We are running your demo of video call and chat on our website <url> me and my developer would like to have a short call with few concerns in prices and customisation. when can we have this call? sure ok but I booked it as well perfect thanks","sentiment_ntlk":"Positive","sentiment_flair":"POSITIVE","sentiment_bert":"NEGATIVE","topic":"thank (0.03) chat (0.03) need (0.01)","chat_record_id":"997894"},
    {"id":"39","review_text":"Me again Wondering if you have anyone that could help with the azure SSO bit I'm guessing i need to make a new app registration in Azure? Not sure where to get the federated data you're looking for Absolutely Thanks Feli Felix I'll leave you in peace now","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"POSITIVE","topic":"thank (0.02) need (0.01) want (0.01)","chat_record_id":"998240"},
    {"id":"40","review_text":"Hi Steve It says no agents available? But i'm logged in and available The agent is in the group and assigned to the queue stewie Smith :) In the SST queue Unfortunately i can't put the widget on the website as our dev who deals with it is on annual leave so just testing through the portal widget section as Felix said i could do that i am yeah set it up this morning I'm logged in to the engage portal and ignite do I need to be logged into both? yeah that's right I'm logged into ignite with a test agent and available I can see the Talkative - SST queue I'm also online in the talkative portal Just removed and re-added to the agent group i can see the group in ignite now Always found YSE to be a bit buggy Thanks for your help Hi Steve Are you still there? So i am in the open media queue  can see it in ignite and i'm available hi Steven sorry internet issues there Yes  so i'm available in ignite on open media but still nothing","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"thank (0.02) need (0.02) agent (0.01)","chat_record_id":"999261"},
    {"id":"41","review_text":"How are you doing today I need updates Sorry it was a mistake On how to catch a real client","sentiment_ntlk":"Negative","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"thank (0.02) need (0.01) want (0.01)","chat_record_id":"1005906"},
    {"id":"42","review_text":"Hi Steve Is there anywhere in the talkative portal that the chat widget is customized like adding an agent photo? I notice you guys had that on your site Or is that just a developer thing on our side? Yeah - i've been using that Is that getting updated? Or is that the updated one? on the latest version is says OK - will give you a shout Thursday Cheers Steve I'm speaking to commsworld re setting up the workflow Not too sure what the settings should be Nothing else for now though Cheers","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"ye (0.02) chat (0.02) agent (0.02)","chat_record_id":"1008040"},
    {"id":"43","review_text":"so do you offer a plugin or what ? ok. so we can integrate it into our wordpress website also do you offer a call back facility from your dashboard so the prices start from 250 $ a month or is there a lesser package its currently not decided yet. but you can tell me if there is any lesser package available also its a contract based system? we are specifically looking for wordpress","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"thank (0.02) need (0.01) want (0.01)","chat_record_id":"1009492"},
    {"id":"44","review_text":"Hi Felix nice to meet you I am Constantinos Senior Developer for Company We are trying to integrate your platform in our system for one of our clients yes I need information on how to get the company-uuid and queue-uuid to setup the CDN ok so the chat-widget will generate the code in the end of the wizard? we need chat voice and screen share though not only chat is there a wizard for all three? I guess we can use script generator for this right? deadline is strict so I dont think they will wait until then the callback functions are the same though? only the initialization is different? I think this week hmm ok so the chat widget  once you start the chat you can then escalate to voice/share yet from the script generator you can also start with voice/share without having to chat first is that correct? How do we call/share? from inside the widget? Screenshot 2021-09-20 125041.png from where? or do you request a call from the agent? so its not available in the new scripts yet where can we ask any developer related questions? May I ask a question from here now? I tried to embed the plugin but it seems that there is a javascript error please see screenshot Screenshot 2021-09-20 130255.png e.map is not a function its local I can share my screen if possible could it be the jquery version we are using or any missing cdns that we need to add? ok thanks please let me know as soon as possible thanks a lot it seems that the CDNs are not loaded properly","sentiment_ntlk":"Positive","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"thank (0.03) good (0.02) think (0.02)","chat_record_id":"1015028"},
    {"id":"45","review_text":"My boss is wanting a few widget design examples I mean it'd jut be the initial layout I guess Thanks Felix What was the situation with the callback feature - is that working now? What options are there to customize the widget when its minimized ? Could we have a call this afternoon ? if you've time Sure yeah - thanks Felix You send a zoom ? Thanks again Felix  appreciate it Speak soon","sentiment_ntlk":"Positive","sentiment_flair":"POSITIVE","sentiment_bert":"POSITIVE","topic":"thank (0.03) good (0.02) think (0.02)","chat_record_id":"1015247"},
    {"id":"46","review_text":"We're looking to put Talkative live on one of our sites tomorrow How does the widget config work with the script generator ? lets do it If you don't mind ok","sentiment_ntlk":"Negative","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"agent (0.03) ye (0.02) access (0.01)","chat_record_id":"1017243"},
    {"id":"47","review_text":"Cannot get account on line to go through I have act code Apple I pad I only have apple I pad Not sure Ok will phone Adrian flux and get document sent in post. Thank you.","sentiment_ntlk":"Negative","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"thank (0.03) appl (0.02) pad (0.02)","chat_record_id":"67"},
    {"id":"48","review_text":"Cannot get account on line to go through I have act code Apple I pad I only have apple I pad Not sure Ok will phone Adrian flux and get document sent in post. Thank you.","sentiment_ntlk":"Negative","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"thank (0.03) appl (0.02) pad (0.02)","chat_record_id":"11"},
    {"id":"49","review_text":"Cannot get account on line to go through I have act code Apple I pad I only have apple I pad Not sure Ok will phone Adrian flux and get document sent in post. Thank you.","sentiment_ntlk":"Negative","sentiment_flair":"NEGATIVE","sentiment_bert":"NEGATIVE","topic":"thank (0.02) appl (0.02) pad (0.02)","chat_record_id":"2003"}];
	var html="";
	for(var i in table){
		html+="<tr><td scope=\"col\">"+table[i].id+"</td><td scope=\"col\" title=\""+table[i].review_text.replace(/\"/g,"'")+"\">"+(table[i].review_text.length>170?(table[i].review_text.replace(/\"/g,"'").substring(0,170)+"..."):table[i].review_text.replace(/\"/g,"'"))+"</td><td scope=\"col\">"+table[i].sentiment_ntlk+"</td><td scope=\"col\">"+table[i].sentiment_flair+"</td><td scope=\"col\">"+table[i].sentiment_bert+"</td><td scope=\"col\">"+table[i].topic+"</td><td scope=\"col\">"+table[i].chat_record_id+"</td></tr>";
	}
	document.getElementById("feedbackUserTableTbody").innerHTML=html;
*/
$("#generate-excel").click(function(){
    var excel=new ExcelGen({"src_id":"feedbackUserTable","show_header":true});
    excel.generate();
});
function loadTotal(){
    getData("/feedbackrecord/count",function(data){
        $("#feedbackTotal").html(data.total);
    });
    getData("/chatrecord/count",function(data){
        $("#chatRecordTotal").html(data.total);
    });
}
function loadPeviewanalysisLits(){
    getData("/reviewanalysis/list",function(data){
        var html="";
        var table=data.list;
        for(var i in table){
            html+="<tr><td scope=\"col\">"+table[i].id+"</td><td scope=\"col\" title=\""+table[i].review_text.replace(/\"/g,"'")+"\">"+(table[i].review_text.length>170?(table[i].review_text.replace(/\"/g,"'").substring(0,170)+"..."):table[i].review_text.replace(/\"/g,"'"))+"</td><td scope=\"col\">"+table[i].sentiment_ntlk+"</td><td scope=\"col\">"+table[i].sentiment_flair+"</td><td scope=\"col\">"+table[i].sentiment_bert+"</td><td scope=\"col\">"+table[i].topic+"</td><td scope=\"col\">"+table[i].chat_record_id+"</td></tr>";
        }
        $("#feedbackUserTableTbody").html(html);
    },'{"page":1,"size":10000}');
}
function loadPeviewanalysisSentiment(){
    getData("/reviewanalysis/sentiment",function(data){
        var ntlk=data.sentiment_ntlk[0];
        var flair=data.sentiment_flair[0];
        var bert=data.sentiment_bert[0];
        optionmyd.series[0].data=[ntlk.Neutral,flair.Neutral,bert.Neutral];
        optionmyd.series[1].data=[ntlk.Negative,flair.Negative,bert.Negative];
        optionmyd.series[2].data=[ntlk.Positive,flair.Positive,bert.Positive];
        var myChart = echarts.init(document.getElementById('chartmain_zhexiao_myd'));
        myChart.setOption(optionmyd);
    });
}
function loadPeviewanalysisTopic(){
    getData("/reviewanalysis/topic",function(data){
        var topic=data.list;
        var keys=data.key;
        var data=[];
        var data2=[];
        for (var key in keys) {
            data.push(topic[keys[key]]);
            data2.push({value: topic[keys[key]], name: keys[key]});
        }
        topicoption.xAxis.data=keys;
        topicoption.series[0].data=data;
        topicoption.series[1].data=data;
        var myChart = echarts.init(document.getElementById('chartmain_tiaoxin_topic'));
        myChart.setOption(topicoption);

        topicoption_bz.legend.data=keys;
        topicoption_bz.series[0].data=data2;
        var myChart = echarts.init(document.getElementById('chartmain_bingzhuan'));
        myChart.setOption(topicoption_bz);
    });
}
loadTotal();
loadPeviewanalysisLits();
loadPeviewanalysisSentiment();
loadPeviewanalysisTopic();