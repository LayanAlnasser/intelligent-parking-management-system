# intelligent-parking-management-system
## High Level Solution: 
The Parking Management System is a Java-based application designed to manage parking areas efficiently, especially in busy environments like the PSU campus. This system simplifies the process of paying parking fees for all active members of PSU, including students, staff, faculty, and visitors. It ensures a smooth and efficient parking experience for everyone by adapting to the varying levels of activity on campus throughout the year.
The system is designed to handle different types of days, such as major events, final exams, regular teaching days, non-teaching days, and completely non-active days. Each of these occasions affects parking lot usage differently, and the system adjusts parking fees accordingly.
For students, the available parking lots are P1, P2, P3, P4, and P5. For staff and faculty, the parking lots are B101, B103, B105, and B106. For higher management, the parking lots are B102 and B104. Visitors can use any available parking lot.
The base parking fees are as follows: students pay 20 SAR, staff and faculty pay 40 SAR, higher management pays 70 SAR, and visitors pay 100 SAR.
The system calculates parking fees based on the type of day and time. During major events, all parking lots are priced at 100% of the original fee. During final exams, all parking lots are priced at 125% of the original fee. On regular teaching days, parking lots are priced at their original fees. On non-teaching days, parking fees are reduced by 25%. On non-business days, parking is free for everyone.

The system also applies time-based adjustments to parking fees. From 6:00 to 8:00, a 15% discount is applied. From 8:00 to 10:00, a 15% increase is applied. From 10:00 to 12:00, no discount is applied. From 12:00 to 13:00, a 15% increase is applied. From 13:00 to 15:00, no discount is applied. From 15:00 to 18:00, a 15% discount is applied. After 18:00, all parking lots are free.
Additionally, a 15% VAT is added to the total fee. If someone wants to upgrade their parking permit, they must pay 15% more than their regular fee. If someone wants to downgrade their parking permit, they pay 15% less than their regular fee.

## Big-oh:
The system’s operations are analyzed using Big O notation. File reading has a time complexity of O(N), where N is the number of lines. The processing phase also has a time complexity of O(N), as it involves operations like removing elements from a linked list and calculating fees. File writing has a time complexity of O(N). The overall time complexity of the system is O(N), and the space complexity is also O(N), as it stores data in a linked list and output strings.

In conclusion, the Parking Management System is an efficient and scalable solution for managing parking fees on the PSU campus. It adapts to different types of days and times, ensuring fair pricing for all users. The system’s time and space complexity are both O(N), making it suitable for handling large datasets efficiently. This project demonstrates the practical application of data structures and algorithms in solving real-world problems. Our opinion for this project that hoping with the future upcoming days that giving this application simple UI to make the system more accessible to non-technical users, such as visitors or staff. 

## Total time:
It takes the whole program which includes reading, processing, and writing output in the process file less than a second to be processed. 
