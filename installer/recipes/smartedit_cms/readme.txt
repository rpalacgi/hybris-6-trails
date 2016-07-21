This installer recipe is for the responsive B2C Accelerator with SmartEdit and the CMS Module components.
Note: For the for the captcha, you must provide your own public/private key.

# 1. Key Components for the recipe
For a full list of extensions and addons, see the build.gradle

# 2. Key extensions:
* cms2
* electronicsstore
* smartedit
* cmssmartedit

# 3. Critical addons:
* smarteditaddon

# 4. SmartEdit and SmartEdit CMS deployed with responsive B2C Accelerator
     1. Install smartedit and the platform using the command ./install.sh -r smartedit_cms
     2. Initialize platform using command ./install.sh -r smartedit_cms initialize
     3. Start the platform using command ./install.sh -r smartedit_cms start
