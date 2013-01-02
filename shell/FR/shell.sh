#! /bin/bash  

IFS=$':'
while read identifier name othername namelang lat lng T1 T2 pays lang2 route dep  num inseecode population T3 T4 zone updatedate  
do 
    echo $identifier $name $lat $lng $dep $inseecode $population $pays
mysql --user=root --password=rabah123 rdfdatabase <<EOF
insert into feature values ('$identifier','$name','$pays','$inseecode','$dep','$lat','$lng', '$population');
EOF


done <$1 2>error.txt



		
