Summary: Skaičių konvertavimo į lietuvių kalbos skaitvardžius biblioteka
Name: skaiciai
Version: 0.1
Release: 1
Group: skaiciai
License: Apache License, Version 2.0
Source: %{expand:%%(pwd)}
BuildRoot: %{_topdir}/BUILD/%{name}-%{version}-%{release}

%description
%{summary}

%prep
rm -rf $RPM_BUILD_ROOT
mkdir -p $RPM_BUILD_ROOT/usr/bin
mkdir -p $RPM_BUILD_ROOT/usr/lib
cd $RPM_BUILD_ROOT
cp %{SOURCEURL0}/skaiciai.sh ./usr/bin/skaiciai
cp %{SOURCEURL0}/../skaitvardziai-app/target/skaitvardziai-*-jar-with-dependencies.jar ./usr/lib/skaiciai.jar

%clean
rm -r -f "$RPM_BUILD_ROOT"

%files
%defattr(644,root,root)
%{_prefix}/lib/skaiciai.jar
%defattr(755,root,root)
%{_prefix}/bin/skaiciai
