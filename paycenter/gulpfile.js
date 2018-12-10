var gulp = require('gulp');
var uglify = require('gulp-uglify');
var rename = require('gulp-rename');
var sourcemaps = require('gulp-sourcemaps');

gulp.task('default', function() {
    gulp.src('src/main/webapp/public/js/ktzhpage.js') // 匹配 'client/js/somedir/somefile.js' 并且将 `base` 解析为 `client/js/`
        .pipe(sourcemaps.init())
        .pipe(rename({suffix: '.min'}))
        .pipe(uglify())
        .pipe(sourcemaps.write('maps'))
        .pipe(gulp.dest('src/main/webapp/public/js'));
});

// minify gaols.validate.js
gulp.task('mini', function() {
    gulp.src('src/main/webapp/public/js/gaols.validate.js') // 匹配 'client/js/somedir/somefile.js' 并且将 `base` 解析为 `client/js/`
        .pipe(sourcemaps.init())
        .pipe(rename({suffix: '.min'}))
        .pipe(uglify())
        .pipe(sourcemaps.write('maps'))
        .pipe(gulp.dest('src/main/webapp/public/js'));
});
