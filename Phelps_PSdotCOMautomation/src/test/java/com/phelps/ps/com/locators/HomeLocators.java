package com.phelps.ps.com.locators;

/**
 * Basic home page
 * 
 * @author amodak
 * 
 */
public interface HomeLocators {

	// Menu Items
	String menuItmStorageXpath = "//a[@id='nav_starting_t']/span";
	String menuItmHowSelfStorageWorksXpath = "//a[contains(text(),'How Storage Works')]";
	String menuItmSelfStorageXpath = "//a[contains(text(),'Self Storage')]";
	String menuItmBusinessStorageXpath = "//a[contains(text(),'Business Storage')]";
	String menuItmVehicleStorageXpath = "//a[contains(text(),'Vehicle Storage')]";
	String menuItmStorageBlogXpath = "//a[contains(text(),'Storage Blog')]";
	String menuItemMovingSuppliesXpath = "//a[contains(text(),'Moving Supplies')]";

	String menuItemStorageBoxesXpath = "//a[contains(text(),'Storage Boxes')]";
	String menuItemStorageLocksXpath = "//a[contains(text(),'Storage Locks')]";
	String menuItemPackagingSuppliesXpath = "//a[contains(text(),'Packing Supplies')]";
	String menuItemTapeXpath = "//a[contains(text(),'Tape')]";

	String menuItemCompanyInfoXpath = "//a[@id='nav_comp_info_t']/span";
	String menuItemAboutUsXpath = "//a[contains(text(),'About Us')]";
	String menuItemInvestorRelationsXpath = "//a[contains(text(),'Investor Relations')]";
	String menuItemGlobalLocationsXpath = "//a[contains(text(),'Global Locations')]";
	String menuItemCareersXpth = "//a[contains(text(),'Careers')]";
	String menuItemContactUsXpath = "//a[contains(text(),'Contact Us')]";
	
	
	String tbContactUsEmailAddressId="cu-form-email";
	String ddContactUsTopidId="cu-form-topic";
	String taContactUsNaturOfInquiryId="cu-form-inquiry";
	String tbContactUsNameId="cu-form-name";
	String tbContactInfoPhone1Id="cu-phone-ac";
	String tbContactInfoPhone3Id="cu-phone-nxxx";
	String tbContactInfoPhone2Id="cu-phone-npa";
	String tbConactUsPhoneExtid="cu-phone-ext";
	String btnContactUsSubmitId="submit1";
	
	
	// Page Verification for Moving Supplies
	String lbPageVerificationStorageBoxesXpath = "//h3[text()='Storage Boxes & Moving Boxes']";
	String lbPageVerificationStorageLocksXpath = "//h3[text()='Storage Locks']";
	String lbPageVerificationPackingSuppliesXpath = "//h3[text()='Packing Supplies']";
	String lbPageVerifcationTapeXpath = "//h3[text()='Carton Sealing Tape']";
	String lbPageVerificationMovingKitsXpath = "//h3[text()='Moving Kits']";
	// Page Verification for Company Info
	String lbPageVerificationAboutUsXpath = " //h1[text()='About Public Storage']";
	String lbPageVerificationInvestorRelationsXpath = "//div[text()='Investor Overview']";
	String lbPageVerificationGlobaLocationsXpath = "//span[text()='Global Locations']";
	String lbPageVerificationCareersXpath = "";
	String lbPageVerificationContactUsXpath = "//h1[text()='Contact Public Storage']";
	// Search area
	String tbSearchName = "location";
	String btnSearchId = "searchButton";

	// Feature boxes
	String hlinkLearnHotToStoreWithUsId = "promos_madesimple_link";
	String hlinkMoreAboutUsId = "promos_madereliable_link";
	String hlinkAccessOurLocationFinderId = "promos_madelocal_link";

	String linkMyAccountText = "My Account";
	String linkPayMyBillText = "Pay My Bill";

	String imgStorageMadeEasyId = "promos_madesimple_img";
	String imgStorageMadeReliableId = "promos_madereliable_img";
	String imgStorageMadeLocalId = "promos_madelocal_img";

	String imgBenefitsXpath = "//img[@class='benefit-img']";
	String imgBlogImageXpath = "//div[@class='col-3']/a";

	String imgLogoImageXpath = "//div[@id='logo']/a/img";
	String imgLogoImageMovingSuppliesXpath = "//div[@id='Hdr_Main']/a";

//	String lbPageVerificationStorageBlogPageXpath = "//a[text()='In Your Space']";
	String lbPageVerificationStorageBlogPageXpath = "//strong[contains(.,'The Organized Life')]";
	// review property
	String btnReviewThisPropertyHomeXpath = ".//div[@class='convo_btn']/input";

	// Review Photos

	String imgHomePhotoXpath = "(.//*[@id='property_info']/table//td[1]/img)[1]";

	String lbHomeAddressXpath = "(.//*[@id='property_info']/table//tr[2]/td)[1]";

	// aug_release

	String lbconvenientAccessXpath = ".//*[@id='section-benifits']/div/a//div[2]/div[4]";
	String lbAccess365MentionXpath = ".//*[@id='section-benifits']//img[@alt='Access 365 days a year']/../div";

	// Home general links
	String linkGetDirectionsHomeXpath = "//*[@id='property_info']/table/tbody/tr[1]/td[2]/span/a[text()='Get Directions']";

	// Home How self storage works
	String linkYourAccountMovingOutOnline = "move123";
	
	//WC2
	//Pending home page 
	String btnECIPendingHomeXpath="//a[@class='hpObtn'][text()='Express Check-in']";//"a[@class='slide-btn']/img[@alt=='Express Check-in']";
	String btnManageYourReservationPendingHomeXpath="//a[@class='hpObtn'][text()='Manage Your Reservations']";//"a[@class='slide-btn']/img[@alt='Manage Your Reservation']";
	
	//Home Page Refresh
	String menuItmActiveHowSelfStorageWorksCss=".submenu.activem>#nav_hsw";
	String menuItmHowStorageWorkId="nav_hsw";
	String menuItmSelfStorageId="nav_self";
	String menuItmBusinessStorageId = "nav_biz";
	String menuItmVehicleStorageId = "nav_vehicle";
	String menuItmSizeGuideId= "nav_guide";
	String menuItmStorageBlogId="blogLink";
	String menuItmActiveStorageCss = ".submenu.activem>#nav_boxes_s";

	String menuItmMovingSuppliesId="nav_boxes_t";
	String menuItmStorageBoxesId = "nav_boxes_s";
	String menuItmPackagingSuppliesId = "nav_supplies";
	String menuItmTapeId = "nav_tape";

	String menuItmCompanytUsId="nav_comp_info_t";
	String menuItmActiveAboutUsCss = ".submenu.activem>#nav_comp_info_s";
	String menuItmAboutUsId = "nav_comp_info_s";
	String menuItmInvestorRelationsId = "nav_inv_rel";
	String menuItmGlobalLocationsId = "nav_global";
	String menuItmCareersId= "nav_jobs";
	String menuItmContactUsId = "nav_contact";
	
	
	String btnSearchCss=".search-btn-hp";
	String iconBenefitUsCss=".icon.us";
	String iconBenefitDollarCss=".icon.dolla";
	String iconBenefitNoCommitmentCss=".icon.cal";
	String lbSizeGuideId="section-size-guide";
	String imgSizeGuideXpath="//img[@src='/images/hp-refresh/size-guide.png']";
	String hlnkSizeGuideTryNowXpath="//a[@href='/storage-units-size-guide.aspx'][text()='Try Now']";
	String canvasSizeGuideId="spinner";
	String lbHowStorageWorkId="section-boxes";
	String btnHowStorageWorksLearnMoreXpath=".//*[@id='boxes-txt']/a";
	
	//Blog
	String lbBlogSectionId="section-blog";
	String imgBlogRecentNewsXpath="//div[@class='blogt'][text()='Recent News']/..//img";
	String imgBlogRecentNewsImageXpath="//img[@src='/images/hp-refresh/blog-post-jersey-city.jpg']";
	String imgBlogSecondXpath="//div[@class='blogt'][text()='Storage Tips']/..//img";
	String imgBlogSecondImageXpath="//img[@src='https://www.publicstorage.com/blog/wp-content/uploads/2017/03/mattress-bags-for-storage.jpg']";
	String imgBlogThirdXpath="//div[@class='blogt'][text()='Moving Tips']/..//img";
	String imgBlogThirdImageXpath="//img[@src='https://www.publicstorage.com/blog/wp-content/uploads/2013/08/two-men-move-furniture-into-a-moving-truck.jpg']";
	String hlnkBlogReadMoreXpath="(.//*[@id=''section-blog'']//a[text()=''Read More''])[{0}]";
	String hlnkBlogXpath=".//*[@id='section-blog']//a[text()='blog']";
	String iconBlogFreeReservationsXpath="//img[@alt='Free Public Storage unit reservations']";
	String iconBlogNoLongTermXpath="//img[@alt='No long-term commitment']";
	String iconBlog40YearsXpath="//img[@alt='Over 40 years of experience']";
	String iconBlogCovenientXpath="//img[@alt='Convenient Access Hours']";
	String iconBlogClimateControllXpath="//img[@alt='Climate-controlled Units']";
	String iconBlogUnitSizesXpath="//img[@alt='Variety of Sizes']";
	String hlnkBenefitSectionXpath="//h2[text()='Why store with us?']/../..";
	String hlnkFacebookCss=".fb";
	String hlnkTwitterCss=".tw";
	String hlnkGooglePlusCss=".goo";
	String hlnkInstragramCss=".inst";
	String hlnkYoutubeCss=".yt";
	
	//How to videos
	String lb1HowToVideosXpath="//h2[text()=\"We're here to help\"]";
	String lb2HowToVideosXpath="Watch our tips & tutorials on moving and storage.";
	String imgHowToVideosXpath=".//*[@id='video-txt']/a/img[@src='/images/hp-refresh/vid-txt-how-to-choose-a-storage-space.png']";
	String hlnkHowToVideoButtonXpath=".//*[@id='video-txt']/a";
	String hlnkHowToVideosAllVideosXpath=".//*[@id='vidLink']/a";
	
	//Recommended locations
	
	String lb1RecommendedLocationTextXpath="//h2[text()=\"We're in your neighborhood\"]";
	String lb2RecommendedLocationText="Recommended locations for you.";
	String lbRecommendedLocationsCouponsCss=".suggLocWrap";
	String lbAllCouponAddressXpath="(//div[@class=''suggLocAddressV2''])[{0}]/a";
	String hlnkAllRecommendedLocationXpath="(//div[@class=''suggLocBoxV2''])[{0}]";//"(//div[@class=''suggLocAddressV2'']//a[@class=''suggUnitLink''])[{0}]";
	String hlnkRightArrowRecommendationLocationXpath="//div[@class='suggCaret suggCaretRight'][@style='display: block;']";
	String lbAllUnitsZixesXpath="(//div[@class='suggLocUnits']//span[@class='suggLocUnitSizeV2'])";
	String lbAllUnitSingleSmallUnitSizesXpath="(((//div[@class=''suggLocUnits''])[{0}])//span[@class=''suggLocUnitSizeV2''])[{1}]";
	String lbAllUnitSingleMediumUnitSizesXpath="(((//div[@class=''suggLocUnits''])[{0}])//span[@class=''suggLocUnitSizeV2''])[{1}]";
	String lbAllUnitSingleLargeUnitSizesXpath="(((//div[@class=''suggLocUnits''])[{0}])//span[@class=''suggLocUnitSizeV2''])[{1}]";
	String lbAllUnitPricesXpath="((((//div[@class=''suggLocUnits''])[{0}])//span[@class=''suggLocUnitPriceV2''])[{1}])[text()=''${2}/mo'']";
	
}
