const profiles = document.querySelectorAll(".profile");
profiles.forEach(profile => profile.classList.add("hidden"));

const profileButtons = document.querySelectorAll(".toggle-profile");
profileButtons.forEach(button => button.addEventListener("click", toggleProfile));

const profileArrows = document.querySelectorAll(".toggle-profile img");

function toggleProfile(e) {
    // obtaining profile button and profile div
    const currentProfileButton = e.target.closest("button");
    const dataId = currentProfileButton.getAttribute("data-id");
    const currentProfileArrow = document.querySelector(`img[data-id='${dataId}']`);
    const currentProfile = document.querySelector(`div[data-id='${dataId}']`);

    // closing/resetting any open profile buttons/divs
    profiles.forEach(profile => {
        if (profile != currentProfile) {
            profile.classList.add("hidden");
            profile.classList.remove("collapse-top-margin");
        }
    });
    profileButtons.forEach(profileButton => {
        if (profileButton != currentProfileButton) profileButton.classList.remove("collapse-bottom-margin");
    });
    profileArrows.forEach(profileArrow => {
        if (profileArrow != currentProfileArrow) profileArrow.setAttribute("src", "down-arrow.svg");
    });

    // opening current profile div
    currentProfileButton.classList.toggle("collapse-bottom-margin");
    currentProfile.classList.toggle("hidden");
    currentProfile.classList.toggle("collapse-top-margin");
    if (currentProfileArrow.getAttribute("src") === "down-arrow.svg") {
        currentProfileArrow.setAttribute("src", "up-arrow.svg");
    } else {
        currentProfileArrow.setAttribute("src", "down-arrow.svg");
    }
}