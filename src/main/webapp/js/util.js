/**
 * utility Funktionen für mehrere Seiten
 *
 * @author  Francesco Ryu
 * @since   18.06.2022
 * @version 3.0
 */

/**
 *
 * @param key
 * @returns {string}
 */
function getQueryParam (key) {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);

    return urlParams.get(key);
}